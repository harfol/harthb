import { DataSetHolder, Datasource, DatasourceType, widgetType } from '@shared/models/widget.models';
import { SubscriptionTimewindow } from '@shared/models/time/time.models';
import { EntityData, EntityDataPageLink, KeyFilter } from '@shared/models/query/query.models';
import { emptyPageData, PageData } from '@shared/models/page/page-data';
import { Injectable } from '@angular/core';
import { TelemetryWebsocketService } from '@core/ws/telemetry-websocket.service';
import { UtilsService } from '@core/services/utils.service';
import { deepClone } from '@core/utils';
import {
  EntityDataSubscription,
  EntityDataSubscriptionOptions,
  SubscriptionDataKey
} from '@core/api/entity-data-subscription';
import { Observable, of } from 'rxjs';

export interface EntityDataListener {
  subscriptionType: widgetType;
  subscriptionTimewindow?: SubscriptionTimewindow;
  configDatasource: Datasource;
  configDatasourceIndex: number;
  dataLoaded: (pageData: PageData<EntityData>,
               data: Array<Array<DataSetHolder>>,
               datasourceIndex: number, pageLink: EntityDataPageLink) => void;
  dataUpdated: (data: DataSetHolder, datasourceIndex: number, dataIndex: number, dataKeyIndex: number, detectChanges: boolean) => void;
  initialPageDataChanged?: (nextPageData: PageData<EntityData>) => void;
  updateRealtimeSubscription?: () => SubscriptionTimewindow;
  setRealtimeSubscription?: (subscriptionTimewindow: SubscriptionTimewindow) => void;
  subscriptionOptions?: EntityDataSubscriptionOptions;
  subscription?: EntityDataSubscription;
}

export interface EntityDataLoadResult {
  pageData: PageData<EntityData>;
  data: Array<Array<DataSetHolder>>;
  datasourceIndex: number;
  pageLink: EntityDataPageLink;
}

@Injectable({
  providedIn: 'root'
})
export class EntityDataService {

  constructor(private telemetryService: TelemetryWebsocketService,
              private utils: UtilsService) {}

  public prepareSubscription(listener: EntityDataListener): Observable<EntityDataLoadResult> {
    const datasource = listener.configDatasource;
    listener.subscriptionOptions = this.createSubscriptionOptions(
      datasource,
      listener.subscriptionType,
      datasource.pageLink,
      datasource.keyFilters,
      null,
      false);
    if (datasource.type === DatasourceType.entity && (!datasource.entityFilter || !datasource.pageLink)) {
      return of(null);
    }
    listener.subscription = new EntityDataSubscription(listener, this.telemetryService, this.utils);
    return listener.subscription.subscribe();
  }

  public startSubscription(listener: EntityDataListener) {
    if (listener.subscription) {
      if (listener.subscriptionType === widgetType.timeseries) {
        listener.subscriptionOptions.subscriptionTimewindow = deepClone(listener.subscriptionTimewindow);
      }
      listener.subscription.start();
    }
  }

  public subscribeForPaginatedData(listener: EntityDataListener,
                                   pageLink: EntityDataPageLink,
                                   keyFilters: KeyFilter[]): Observable<EntityDataLoadResult> {
    const datasource = listener.configDatasource;
    listener.subscriptionOptions = this.createSubscriptionOptions(
      datasource,
      listener.subscriptionType,
      pageLink,
      datasource.keyFilters,
      keyFilters,
      true);
    if (datasource.type === DatasourceType.entity && (!datasource.entityFilter || !pageLink)) {
      listener.dataLoaded(emptyPageData<EntityData>(), [],
        listener.configDatasourceIndex, listener.subscriptionOptions.pageLink);
      return of(null);
    }
    listener.subscription = new EntityDataSubscription(listener, this.telemetryService, this.utils);
    if (listener.subscriptionType === widgetType.timeseries) {
      listener.subscriptionOptions.subscriptionTimewindow = deepClone(listener.subscriptionTimewindow);
    }
    return listener.subscription.subscribe();
  }

  public stopSubscription(listener: EntityDataListener) {
    if (listener.subscription) {
      listener.subscription.unsubscribe();
    }
  }

  private createSubscriptionOptions(datasource: Datasource,
                                    subscriptionType: widgetType,
                                    pageLink: EntityDataPageLink,
                                    keyFilters: KeyFilter[],
                                    additionalKeyFilters: KeyFilter[],
                                    isPaginatedDataSubscription: boolean): EntityDataSubscriptionOptions {
    const subscriptionDataKeys: Array<SubscriptionDataKey> = [];
    datasource.dataKeys.forEach((dataKey) => {
      const subscriptionDataKey: SubscriptionDataKey = {
        name: dataKey.name,
        type: dataKey.type,
        funcBody: dataKey.funcBody,
        postFuncBody: dataKey.postFuncBody
      };
      subscriptionDataKeys.push(subscriptionDataKey);
    });
    const entityDataSubscriptionOptions: EntityDataSubscriptionOptions = {
      datasourceType: datasource.type,
      dataKeys: subscriptionDataKeys,
      type: subscriptionType
    };
    if (entityDataSubscriptionOptions.datasourceType === DatasourceType.entity) {
      entityDataSubscriptionOptions.entityFilter = datasource.entityFilter;
      entityDataSubscriptionOptions.pageLink = pageLink;
      entityDataSubscriptionOptions.keyFilters = keyFilters;
      entityDataSubscriptionOptions.additionalKeyFilters = additionalKeyFilters;
    }
    entityDataSubscriptionOptions.isPaginatedDataSubscription = isPaginatedDataSubscription;
    return entityDataSubscriptionOptions;
  }
}

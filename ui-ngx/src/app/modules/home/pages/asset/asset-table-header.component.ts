import { Component } from '@angular/core';
import { Store } from '@ngrx/store';
import { AppState } from '@core/core.state';
import { EntityTableHeaderComponent } from '../../components/entity/entity-table-header.component';
import { EntityType } from '@shared/models/entity-type.models';
import { AssetInfo } from '@shared/models/asset.models';

@Component({
  selector: 'tb-asset-table-header',
  templateUrl: './asset-table-header.component.html',
  styleUrls: ['./asset-table-header.component.scss']
})
export class AssetTableHeaderComponent extends EntityTableHeaderComponent<AssetInfo> {

  entityType = EntityType;

  constructor(protected store: Store<AppState>) {
    super(store);
  }

  assetTypeChanged(assetType: string) {
    this.entitiesTableConfig.componentsData.assetType = assetType;
    this.entitiesTableConfig.table.resetSortAndFilter(true);
  }

}

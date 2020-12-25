import { Injectable } from '@angular/core';
import { defaultHttpOptionsFromConfig, RequestConfig } from './http-utils';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { TimePageLink } from '@shared/models/page/page-link';
import { PageData } from '@shared/models/page/page-data';
import { EntityId } from '@shared/models/id/entity-id';
import { DebugEventType, Event, EventType } from '@shared/models/event.models';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  constructor(
    private http: HttpClient
  ) { }

  public getEvents(entityId: EntityId, eventType: EventType | DebugEventType, tenantId: string, pageLink: TimePageLink,
                   config?: RequestConfig): Observable<PageData<Event>> {
    return this.http.get<PageData<Event>>(`/api/events/${entityId.entityType}/${entityId.id}/${eventType}` +
              `${pageLink.toQuery()}&tenantId=${tenantId}`,
      defaultHttpOptionsFromConfig(config));
  }
}

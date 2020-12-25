import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { defaultHttpOptionsFromConfig, RequestConfig } from '@core/http/http-utils';
import { Observable } from 'rxjs';
import { ServiceType } from '@shared/models/queue.models';

@Injectable({
  providedIn: 'root'
})
export class QueueService {

  constructor(
    private http: HttpClient
  ) { }

  public getTenantQueuesByServiceType(serviceType: ServiceType, config?: RequestConfig): Observable<Array<string>> {
    return this.http.get<Array<string>>(`/api/tenant/queues?serviceType=${serviceType}`,
      defaultHttpOptionsFromConfig(config));
  }
}

import { Injectable } from '@angular/core';
import { defaultHttpOptionsFromConfig, RequestConfig } from './http-utils';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import {
  AdminSettings,
  MailServerSettings,
  SecuritySettings,
  TestSmsRequest,
  UpdateMessage
} from '@shared/models/settings.models';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(
    private http: HttpClient
  ) { }

  public getAdminSettings<T>(key: string, config?: RequestConfig): Observable<AdminSettings<T>> {
    return this.http.get<AdminSettings<T>>(`/api/admin/settings/${key}`, defaultHttpOptionsFromConfig(config));
  }

  public saveAdminSettings<T>(adminSettings: AdminSettings<T>,
                              config?: RequestConfig): Observable<AdminSettings<T>> {
    return this.http.post<AdminSettings<T>>('/api/admin/settings', adminSettings, defaultHttpOptionsFromConfig(config));
  }

  public sendTestMail(adminSettings: AdminSettings<MailServerSettings>,
                      config?: RequestConfig): Observable<void> {
    return this.http.post<void>('/api/admin/settings/testMail', adminSettings, defaultHttpOptionsFromConfig(config));
  }

  public sendTestSms(testSmsRequest: TestSmsRequest,
                     config?: RequestConfig): Observable<void> {
    return this.http.post<void>('/api/admin/settings/testSms', testSmsRequest, defaultHttpOptionsFromConfig(config));
  }

  public getSecuritySettings(config?: RequestConfig): Observable<SecuritySettings> {
    return this.http.get<SecuritySettings>(`/api/admin/securitySettings`, defaultHttpOptionsFromConfig(config));
  }

  public saveSecuritySettings(securitySettings: SecuritySettings,
                              config?: RequestConfig): Observable<SecuritySettings> {
    return this.http.post<SecuritySettings>('/api/admin/securitySettings', securitySettings,
      defaultHttpOptionsFromConfig(config));
  }

  public checkUpdates(config?: RequestConfig): Observable<UpdateMessage> {
    return this.http.get<UpdateMessage>(`/api/admin/updates`, defaultHttpOptionsFromConfig(config));
  }
}

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { defaultHttpOptionsFromConfig, RequestConfig } from '@core/http/http-utils';
import { Observable } from 'rxjs';
import { OAuth2ClientRegistrationTemplate, OAuth2ClientsParams } from '@shared/models/oauth2.models';

@Injectable({
  providedIn: 'root'
})
export class OAuth2Service {

  constructor(
    private http: HttpClient
  ) { }

  public getOAuth2Settings(config?: RequestConfig): Observable<OAuth2ClientsParams> {
    return this.http.get<OAuth2ClientsParams>(`/api/oauth2/config`, defaultHttpOptionsFromConfig(config));
  }

  public getOAuth2Template(config?: RequestConfig): Observable<Array<OAuth2ClientRegistrationTemplate>> {
    return this.http.get<Array<OAuth2ClientRegistrationTemplate>>(`/api/oauth2/config/template`, defaultHttpOptionsFromConfig(config));
  }

  public saveOAuth2Settings(OAuth2Setting: OAuth2ClientsParams, config?: RequestConfig): Observable<OAuth2ClientsParams> {
    return this.http.post<OAuth2ClientsParams>('/api/oauth2/config', OAuth2Setting,
      defaultHttpOptionsFromConfig(config));
  }

  public getLoginProcessingUrl(config?: RequestConfig): Observable<string> {
    return this.http.get<string>(`/api/oauth2/loginProcessingUrl`, defaultHttpOptionsFromConfig(config));
  }
}

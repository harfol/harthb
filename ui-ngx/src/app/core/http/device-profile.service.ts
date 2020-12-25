import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PageLink } from '@shared/models/page/page-link';
import { defaultHttpOptionsFromConfig, RequestConfig } from './http-utils';
import { Observable } from 'rxjs';
import { PageData } from '@shared/models/page/page-data';
import { DeviceProfile, DeviceProfileInfo, DeviceTransportType } from '@shared/models/device.models';
import { isDefinedAndNotNull } from '@core/utils';

@Injectable({
  providedIn: 'root'
})
export class DeviceProfileService {

  constructor(
    private http: HttpClient
  ) { }

  public getDeviceProfiles(pageLink: PageLink, config?: RequestConfig): Observable<PageData<DeviceProfile>> {
    return this.http.get<PageData<DeviceProfile>>(`/api/deviceProfiles${pageLink.toQuery()}`, defaultHttpOptionsFromConfig(config));
  }

  public getDeviceProfile(deviceProfileId: string, config?: RequestConfig): Observable<DeviceProfile> {
    return this.http.get<DeviceProfile>(`/api/deviceProfile/${deviceProfileId}`, defaultHttpOptionsFromConfig(config));
  }

  public saveDeviceProfile(deviceProfile: DeviceProfile, config?: RequestConfig): Observable<DeviceProfile> {
    return this.http.post<DeviceProfile>('/api/deviceProfile', deviceProfile, defaultHttpOptionsFromConfig(config));
  }

  public deleteDeviceProfile(deviceProfileId: string, config?: RequestConfig) {
    return this.http.delete(`/api/deviceProfile/${deviceProfileId}`, defaultHttpOptionsFromConfig(config));
  }

  public setDefaultDeviceProfile(deviceProfileId: string, config?: RequestConfig): Observable<DeviceProfile> {
    return this.http.post<DeviceProfile>(`/api/deviceProfile/${deviceProfileId}/default`, defaultHttpOptionsFromConfig(config));
  }

  public getDefaultDeviceProfileInfo(config?: RequestConfig): Observable<DeviceProfileInfo> {
    return this.http.get<DeviceProfileInfo>('/api/deviceProfileInfo/default', defaultHttpOptionsFromConfig(config));
  }

  public getDeviceProfileInfo(deviceProfileId: string, config?: RequestConfig): Observable<DeviceProfileInfo> {
    return this.http.get<DeviceProfileInfo>(`/api/deviceProfileInfo/${deviceProfileId}`, defaultHttpOptionsFromConfig(config));
  }

  public getDeviceProfileInfos(pageLink: PageLink, transportType?: DeviceTransportType,
                               config?: RequestConfig): Observable<PageData<DeviceProfileInfo>> {
    let url = `/api/deviceProfileInfos${pageLink.toQuery()}`;
    if (isDefinedAndNotNull(transportType)) {
      url += `&transportType=${transportType}`;
    }
    return this.http.get<PageData<DeviceProfileInfo>>(url, defaultHttpOptionsFromConfig(config));
  }

  public getDeviceProfileDevicesAttributesKeys(deviceProfileId?: string, config?: RequestConfig): Observable<Array<string>> {
    let url = `/api/deviceProfile/devices/keys/attributes`;
    if (isDefinedAndNotNull(deviceProfileId)) {
      url += `?deviceProfileId=${deviceProfileId}`;
    }
    return this.http.get<Array<string>>(url, defaultHttpOptionsFromConfig(config));
  }

  public getDeviceProfileDevicesTimeseriesKeys(deviceProfileId?: string, config?: RequestConfig): Observable<Array<string>> {
    let url = `/api/deviceProfile/devices/keys/timeseries`;
    if (isDefinedAndNotNull(deviceProfileId)) {
      url += `?deviceProfileId=${deviceProfileId}`;
    }
    return this.http.get<Array<string>>(url, defaultHttpOptionsFromConfig(config));
  }

}

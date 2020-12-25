import { ContactBased } from '@shared/models/contact-based.model';
import { TenantId } from './id/tenant-id';
import { TenantProfileId } from '@shared/models/id/tenant-profile-id';
import { BaseData } from '@shared/models/base-data';

export enum TenantProfileType {
  DEFAULT = 'DEFAULT'
}

export interface DefaultTenantProfileConfiguration {
  maxDevices: number;
  maxAssets: number;
  maxCustomers: number;
  maxUsers: number;
  maxDashboards: number;
  maxRuleChains: number;

  transportTenantMsgRateLimit?: string;
  transportTenantTelemetryMsgRateLimit?: string;
  transportTenantTelemetryDataPointsRateLimit?: string;
  transportDeviceMsgRateLimit?: string;
  transportDeviceTelemetryMsgRateLimit?: string;
  transportDeviceTelemetryDataPointsRateLimit?: string;

  maxTransportMessages: number;
  maxTransportDataPoints: number;
  maxREExecutions: number;
  maxJSExecutions: number;
  maxDPStorageDays: number;
  maxRuleNodeExecutionsPerMessage: number;
  maxEmails: number;
  maxSms: number;

  defaultStorageTtlDays: number;
}

export type TenantProfileConfigurations = DefaultTenantProfileConfiguration;

export interface TenantProfileConfiguration extends TenantProfileConfigurations {
  type: TenantProfileType;
}

export function createTenantProfileConfiguration(type: TenantProfileType): TenantProfileConfiguration {
  let configuration: TenantProfileConfiguration = null;
  if (type) {
    switch (type) {
      case TenantProfileType.DEFAULT:
        const defaultConfiguration: DefaultTenantProfileConfiguration = {
          maxDevices: 0,
          maxAssets: 0,
          maxCustomers: 0,
          maxUsers: 0,
          maxDashboards: 0,
          maxRuleChains: 0,
          maxTransportMessages: 0,
          maxTransportDataPoints: 0,
          maxREExecutions: 0,
          maxJSExecutions: 0,
          maxDPStorageDays: 0,
          maxRuleNodeExecutionsPerMessage: 0,
          maxEmails: 0,
          maxSms: 0,
          defaultStorageTtlDays: 0
        };
        configuration = {...defaultConfiguration, type: TenantProfileType.DEFAULT};
        break;
    }
  }
  return configuration;
}

export interface TenantProfileData {
  configuration: TenantProfileConfiguration;
}

export interface TenantProfile extends BaseData<TenantProfileId> {
  name: string;
  description?: string;
  default?: boolean;
  isolatedTbCore?: boolean;
  isolatedTbRuleEngine?: boolean;
  profileData?: TenantProfileData;
}

export interface Tenant extends ContactBased<TenantId> {
  title: string;
  region: string;
  tenantProfileId: TenantProfileId;
  additionalInfo?: any;
}

export interface TenantInfo extends Tenant {
  tenantProfileName: string;
}

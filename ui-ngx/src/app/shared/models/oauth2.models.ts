import { HasUUID } from '@shared/models/id/has-uuid';

export interface OAuth2ClientsParams {
  enabled: boolean;
  domainsParams: OAuth2ClientsDomainParams[];
}

export interface OAuth2ClientsDomainParams {
  clientRegistrations: ClientRegistration[];
  domainInfos: DomainInfo[];
}

export interface DomainInfo {
  name: string;
  scheme: DomainSchema;
}

export enum DomainSchema{
  HTTP = 'HTTP',
  HTTPS = 'HTTPS',
  MIXED = 'MIXED'
}

export const domainSchemaTranslations = new Map<DomainSchema, string>(
  [
    [DomainSchema.HTTP, 'admin.oauth2.domain-schema-http'],
    [DomainSchema.HTTPS, 'admin.oauth2.domain-schema-https'],
    [DomainSchema.MIXED, 'admin.oauth2.domain-schema-mixed']
  ]
);

export enum MapperConfigType{
  BASIC = 'BASIC',
  CUSTOM = 'CUSTOM',
  GITHUB = 'GITHUB'
}

export enum TenantNameStrategy{
  DOMAIN = 'DOMAIN',
  EMAIL = 'EMAIL',
  CUSTOM = 'CUSTOM'
}

export interface OAuth2ClientRegistrationTemplate extends ClientRegistration{
  comment: string;
  createdTime: number;
  helpLink: string;
  name: string;
  providerId: string;
  id: HasUUID;
}

export interface ClientRegistration {
  loginButtonLabel: string;
  loginButtonIcon: string;
  clientId: string;
  clientSecret: string;
  accessTokenUri: string;
  authorizationUri: string;
  scope: string[];
  jwkSetUri?: string;
  userInfoUri: string;
  clientAuthenticationMethod: ClientAuthenticationMethod;
  userNameAttributeName: string;
  mapperConfig: MapperConfig;
  additionalInfo: string;
}

export enum ClientAuthenticationMethod {
  BASIC = 'BASIC',
  POST = 'POST'
}

export interface MapperConfig {
  allowUserCreation: boolean;
  activateUser: boolean;
  type: MapperConfigType;
  basic?: MapperConfigBasic;
  custom?: MapperConfigCustom;
}

export interface MapperConfigBasic {
  emailAttributeKey: string;
  firstNameAttributeKey?: string;
  lastNameAttributeKey?: string;
  tenantNameStrategy: TenantNameStrategy;
  tenantNamePattern?: string;
  customerNamePattern?: string;
  defaultDashboardName?: string;
  alwaysFullScreen?: boolean;
}

export interface MapperConfigCustom {
  url: string;
  username?: string;
  password?: string;
}

export interface OAuth2ClientInfo {
  name: string;
  icon?: string;
  url: string;
}

import {
  googleMapSettingsSchema, hereMapSettingsSchema, imageMapSettingsSchema,
  openstreetMapSettingsSchema,
  tencentMapSettingsSchema
} from '@home/components/widget/lib/maps/schemes';
import { OpenStreetMap } from './openstreet-map';
import { TencentMap } from './tencent-map';
import { GoogleMap } from './google-map';
import { HEREMap } from './here-map';
import { ImageMap } from './image-map';
import { Type } from '@angular/core';
import LeafletMap from '@home/components/widget/lib/maps/leaflet-map';
import { JsonSettingsSchema } from '@shared/models/widget.models';

interface IProvider {
  MapClass: Type<LeafletMap>;
  schema: JsonSettingsSchema;
  name: string;
}

export const providerSets: { [key: string]: IProvider } = {
  'openstreet-map': {
    MapClass: OpenStreetMap,
    schema: openstreetMapSettingsSchema,
    name: 'openstreet-map'
  },
  'tencent-map': {
    MapClass: TencentMap,
    schema: tencentMapSettingsSchema,
    name: 'tencent-map'
  },
  'google-map': {
    MapClass: GoogleMap,
    schema: googleMapSettingsSchema,
    name: 'google-map'
  },
  here: {
    MapClass: HEREMap,
    schema: hereMapSettingsSchema,
    name: 'here'
  },
  'image-map': {
    MapClass: ImageMap,
    schema: imageMapSettingsSchema,
    name: 'image-map'
  }
};

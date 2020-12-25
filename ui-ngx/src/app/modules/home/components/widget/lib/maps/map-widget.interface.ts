import { JsonSettingsSchema } from '@shared/models/widget.models';
import { MapProviders } from './map-models';

export interface MapWidgetInterface {
    resize();
    update();
    onInit();
    onDestroy();
}

export interface MapWidgetStaticInterface {
    settingsSchema(mapProvider?: MapProviders, drawRoutes?: boolean): JsonSettingsSchema;
    getProvidersSchema(mapProvider?: MapProviders, ignoreImageMap?: boolean): JsonSettingsSchema;
    dataKeySettingsSchema(): object;
    actionSources(): object;
}

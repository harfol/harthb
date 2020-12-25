import L from 'leaflet';
import LeafletMap from '../leaflet-map';
import { DEFAULT_ZOOM_LEVEL, UnitedMapSettings } from '../map-models';
import { WidgetContext } from '@home/models/widget-component.models';

export class HEREMap extends LeafletMap {
    constructor(ctx: WidgetContext, $container, options: UnitedMapSettings) {
        super(ctx, $container, options);
        const map = L.map($container, {
          editable: !!options.editablePolygon
        }).setView(options?.defaultCenterPosition, options?.defaultZoomLevel || DEFAULT_ZOOM_LEVEL);
        const tileLayer = (L.tileLayer as any).provider(options.mapProviderHere || 'HERE.normalDay', options.credentials);
        tileLayer.addTo(map);
        super.initSettings(options);
        super.setMap(map);
    }
}

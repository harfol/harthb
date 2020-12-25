import L from 'leaflet';
import LeafletMap from '../leaflet-map';
import { DEFAULT_ZOOM_LEVEL, UnitedMapSettings } from '../map-models';
import { WidgetContext } from '@home/models/widget-component.models';

export class OpenStreetMap extends LeafletMap {
    constructor(ctx: WidgetContext, $container, options: UnitedMapSettings) {
        super(ctx, $container, options);
        const map =  L.map($container, {
          editable: !!options.editablePolygon
        }).setView(options?.defaultCenterPosition, options?.defaultZoomLevel || DEFAULT_ZOOM_LEVEL);
        let tileLayer;
        if (options.useCustomProvider) {
          tileLayer = L.tileLayer(options.customProviderTileUrl);
        } else {
          tileLayer = (L.tileLayer as any).provider(options.mapProvider || 'OpenStreetMap.Mapnik');
        }
        tileLayer.addTo(map);
        super.initSettings(options);
        super.setMap(map);
    }
}

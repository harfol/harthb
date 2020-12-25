import * as L from 'leaflet'

declare module 'leaflet' {

    namespace Control {
        class AddMarker extends L.Control { }
        class AddPolygon extends L.Control { }
    }

    namespace control {
        function addMarker(options): Control.AddMarker;
        function addPolygon(options): Control.AddPolygon;
    }

}

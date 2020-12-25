package org.thingsboard.rule.engine.geo;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.context.jts.JtsSpatialContext;
import org.locationtech.spatial4j.context.jts.JtsSpatialContextFactory;
import org.locationtech.spatial4j.distance.DistanceUtils;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Shape;
import org.locationtech.spatial4j.shape.ShapeFactory;
import org.locationtech.spatial4j.shape.SpatialRelation;

public class GeoUtil {

    private static final SpatialContext distCtx = SpatialContext.GEO;
    private static final JtsSpatialContext jtsCtx;

    static {
        JtsSpatialContextFactory factory = new JtsSpatialContextFactory();
        factory.normWrapLongitude = true;
        jtsCtx = factory.newSpatialContext();
    }

    public static synchronized double distance(Coordinates x, Coordinates y, RangeUnit unit) {
        Point xLL = distCtx.getShapeFactory().pointXY(x.getLongitude(), x.getLatitude());
        Point yLL = distCtx.getShapeFactory().pointXY(y.getLongitude(), y.getLatitude());
        return unit.fromKm(distCtx.getDistCalc().distance(xLL, yLL) * DistanceUtils.DEG_TO_KM);
    }

    public static synchronized boolean contains(String polygon, Coordinates coordinates) {
        ShapeFactory.PolygonBuilder polygonBuilder = jtsCtx.getShapeFactory().polygon();
        JsonArray polygonArray = new JsonParser().parse(polygon).getAsJsonArray();
        boolean first = true;
        double firstLat = 0.0;
        double firstLng = 0.0;
        for (JsonElement jsonElement : polygonArray) {
            double lat = jsonElement.getAsJsonArray().get(0).getAsDouble();
            double lng = jsonElement.getAsJsonArray().get(1).getAsDouble();
            if (first) {
                firstLat = lat;
                firstLng = lng;
                first = false;
            }
            polygonBuilder.pointXY(jtsCtx.getShapeFactory().normX(lng), jtsCtx.getShapeFactory().normY(lat));
        }
        polygonBuilder.pointXY(jtsCtx.getShapeFactory().normX(firstLng), jtsCtx.getShapeFactory().normY(firstLat));
        Shape shape = polygonBuilder.buildOrRect();
        Point point = jtsCtx.makePoint(coordinates.getLongitude(), coordinates.getLatitude());
        return shape.relate(point).equals(SpatialRelation.CONTAINS);
    }
}

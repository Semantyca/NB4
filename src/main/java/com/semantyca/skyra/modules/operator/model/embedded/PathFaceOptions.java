package com.semantyca.skyra.modules.operator.model.embedded;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.semantyca.nb.util.StringUtil;

public class PathFaceOptions {

    @JsonProperty("strokeColor")
    private String color = StringUtil.getRndColor();

    @JsonProperty("strokeWeight")
    private int weight = 2;

    private double strokeOpacity = 1.0;

    private boolean geodesic;


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getStrokeOpacity() {
        return strokeOpacity;
    }

    public void setStrokeOpacity(double strokeOpacity) {
        this.strokeOpacity = strokeOpacity;
    }

    public boolean isGeodesic() {
        return geodesic;
    }

    public void setGeodesic(boolean geodesic) {
        this.geodesic = geodesic;
    }
}

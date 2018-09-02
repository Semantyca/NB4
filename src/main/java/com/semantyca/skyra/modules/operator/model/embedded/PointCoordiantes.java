package com.semantyca.skyra.modules.operator.model.embedded;


//@Embeddable
public class PointCoordiantes {
    private float lng;
    private float lat;

    public PointCoordiantes(String lng, String lat) {
        this.lng = Float.parseFloat(lng.replace("0 ", ""));
        this.lat = Float.parseFloat(lat.replace("0 ", ""));
    }

    public PointCoordiantes() {

    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

}
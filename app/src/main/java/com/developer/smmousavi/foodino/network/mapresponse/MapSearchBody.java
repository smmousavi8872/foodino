package com.developer.smmousavi.foodino.network.mapresponse;

public class MapSearchBody {

    private final String text;
    private final String $select;
    private final double lon;
    private final double lat;

    public MapSearchBody(String text, String $select, double lon, double lat) {
        this.text = text;
        this.$select = $select;
        this.lon = lon;
        this.lat = lat;
    }
}

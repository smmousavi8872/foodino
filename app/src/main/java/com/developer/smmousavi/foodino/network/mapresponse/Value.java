package com.developer.smmousavi.foodino.network.mapresponse;

public class Value {

    private String province;
    private String county;
    private String district;
    private String city;
    private String region;
    private String neighborhood;
    private String title;
    private String address;
    private String type;
    private String fclass;
    private Geom geom;


    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFclass() {
        return fclass;
    }

    public void setFclass(String fclass) {
        this.fclass = fclass;
    }

    public Geom getGeom() {
        return geom;
    }

    public void setGeom(Geom geom) {
        this.geom = geom;
    }


    @Override
    public String toString() {
        return "Value{" +
            "province='" + province + '\'' +
            ", county='" + county + '\'' +
            ", district='" + district + '\'' +
            ", city='" + city + '\'' +
            ", region='" + region + '\'' +
            ", neighborhood='" + neighborhood + '\'' +
            ", title='" + title + '\'' +
            ", address='" + address + '\'' +
            ", type='" + type + '\'' +
            ", fclass='" + fclass + '\'' +
            ", geom=" + geom +
            '}';
    }
}

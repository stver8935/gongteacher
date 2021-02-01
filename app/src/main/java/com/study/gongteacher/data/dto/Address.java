package com.study.gongteacher.data.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address {

    @Expose
    @SerializedName("location_id")
    private int locationId;

    @Expose
    @SerializedName("location")
    private String location;

    @Expose
    @SerializedName("town")
    private String town;

    @Expose
    @SerializedName("page")
    private int page;

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}

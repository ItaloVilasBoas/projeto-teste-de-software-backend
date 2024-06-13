package com.movielist.external.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductionCompanyDTO{
    
    private int id;

    @JsonProperty("logo_path")
    private String logoPath;

    private String name;

    @JsonProperty("origin_country")
    private String originCountry;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLogoPath() {
        return logoPath;
    }
    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getOriginCountry() {
        return originCountry;
    }
    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }


}

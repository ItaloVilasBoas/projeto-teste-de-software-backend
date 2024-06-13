package com.movielist.external.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductionCountryDTO {
    @JsonProperty("iso_3166_1")
    private String abreviation;
    private String name;

    public String getAbreviation() {
        return abreviation;
    }

    public void setAbreviation(String abreviation) {
        this.abreviation = abreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

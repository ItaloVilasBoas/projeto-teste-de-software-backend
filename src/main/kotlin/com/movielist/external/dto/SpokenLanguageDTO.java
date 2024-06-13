package com.movielist.external.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpokenLanguageDTO {
    @JsonProperty("english_name")
    private String engnlishName;
    @JsonProperty("iso_639_1")
    private String abreviation;
    @JsonProperty("name")
    private String originalName;

    public String getEngnlishName() {
        return engnlishName;
    }

    public void setEngnlishName(String engnlishName) {
        this.engnlishName = engnlishName;
    }

    public String getAbreviation() {
        return abreviation;
    }

    public void setAbreviation(String abreviation) {
        this.abreviation = abreviation;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }
}

package com.movielist.external.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VideoDTO {
    @JsonProperty("iso_639_1")
    private String languageAbreviation;

    @JsonProperty("iso_3166_1")
    private String countryAbreviation;

    @JsonProperty("name")
    private String name;

    @JsonProperty("key")
    private String key;

    @JsonProperty("site")
    private String site;

    @JsonProperty("size")
    private String size;

    @JsonProperty("type")
    private String type;

    @JsonProperty("official")
    private Boolean official;

    @JsonProperty("id")
    private String id;

    public String getLanguageAbreviation() {
        return languageAbreviation;
    }

    public void setLanguageAbreviation(String languageAbreviation) {
        this.languageAbreviation = languageAbreviation;
    }

    public String getCountryAbreviation() {
        return countryAbreviation;
    }

    public void setCountryAbreviation(String countryAbreviation) {
        this.countryAbreviation = countryAbreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getOfficial() {
        return official;
    }

    public void setOfficial(Boolean official) {
        this.official = official;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

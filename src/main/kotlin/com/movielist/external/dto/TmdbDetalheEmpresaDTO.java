package com.movielist.external.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TmdbDetalheEmpresaDTO {

    @JsonProperty("description")
    private String description;

    @JsonProperty("headquerters")
    private String headquerters;

    @JsonProperty("homepage")
    private String homepage;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("logo_path")
    private String  logoPath;

    @JsonProperty("name")
    private String name;

    @JsonProperty("origin_country")
    private String originCountry;

    @JsonProperty("parent_company")
    private TmdbEmpresaDTO parentCompany;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHeadquerters() {
        return headquerters;
    }

    public void setHeadquerters(String headquerters) {
        this.headquerters = headquerters;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public TmdbEmpresaDTO getParentCompany() {
        return parentCompany;
    }

    public void setParentCompany(TmdbEmpresaDTO parentCompany) {
        this.parentCompany = parentCompany;
    }
}

package com.movielist.external.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TmdbPessoaDTO {
    @JsonProperty("adult")
    private Boolean adult;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("known_for_department")
    private String knownForDepartment;

    @JsonProperty("name")
    private String name;

    @JsonProperty("popularity")
    private Double popularity;

    @JsonProperty("profile_path")
    private String profilePath;

    @JsonProperty("known_for")
    private List<TmdbFilmeSerieDTO> knownFor;

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKnownForDepartment() {
        return knownForDepartment;
    }

    public void setKnownForDepartment(String knownForDepartment) {
        this.knownForDepartment = knownForDepartment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public List<TmdbFilmeSerieDTO> getKnownFor() {
        return knownFor;
    }

    public void setKnownFor(List<TmdbFilmeSerieDTO> knownFor) {
        this.knownFor = knownFor;
    }
}

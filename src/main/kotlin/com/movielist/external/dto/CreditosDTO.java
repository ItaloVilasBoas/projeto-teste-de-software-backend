package com.movielist.external.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class CreditosDTO {

    @JsonProperty("cast")
    private List<TmdbElencoDTO> cast = new ArrayList<>();
    @JsonProperty("crew")
    private List<TmdbElencoDTO> crew = new ArrayList<>();;

    public List<TmdbElencoDTO> getCast() {
        return cast;
    }

    public void setCast(List<TmdbElencoDTO> cast) {
        this.cast = cast;
    }

    public List<TmdbElencoDTO> getCrew() {
        return crew;
    }

    public void setCrew(List<TmdbElencoDTO> crew) {
        this.crew = crew;
    }
}

package com.movielist.external.dto

import com.fasterxml.jackson.annotation.JsonProperty

class TmdbDetalheFilmeDTO {
    @JsonProperty("adult")
     var adult: Boolean? = null

    @JsonProperty("backdrop_path")
     var backdropPath: String? = null

    @JsonProperty("belongs_to_collection")
     var belongsToCollection: Any? = null

    @JsonProperty("budget")
     var budget: Double? = null

    @JsonProperty("genres")
     var genres: List<GenreDTO> = ArrayList()

    @JsonProperty("homepage")
     var homepage: String? = null

    @JsonProperty("id")
    var id: Long? = null

    @JsonProperty("imdb_id")
     var imdbId: String? = null

    @JsonProperty("original_language")
     var originalLanguage: String? = null

    @JsonProperty("original_title")
     var originalTitle: String? = null

    @JsonProperty("overview")
     var overview: String? = null

    @JsonProperty("popularity")
     var popularity: Double? = null

    @JsonProperty("poster_path")
     var posterPath: String? = null

    @JsonProperty("production_companies")
     var productionCompanies: List<ProductionCompanyDTO> = ArrayList()

    @JsonProperty("production_countries")
     var productionCountries: List<ProductionCountryDTO> = ArrayList()

    @JsonProperty("release_date")
     var releaseDate: String? = null

    @JsonProperty("revenue")
     var revenue: Int? = null

    @JsonProperty("runtime")
     var runtime: Int? = null

    @JsonProperty("spoken_languages")
     var spokenLanguages: List<SpokenLanguageDTO> = ArrayList()

    @JsonProperty("status")
     var status: String? = null

    @JsonProperty("tagline")
     var tagline: String? = null

    @JsonProperty("title")
     var title: String? = null

    @JsonProperty("video")
     var video: Boolean? = null

    @JsonProperty("vote_average")
     var voteAverage: Double? = null

    @JsonProperty("vote_count")
     var voteCount: Int? = null

    @JsonProperty("vidoes.results")
     var videos: TmdbCabecalhoDTO<VideoDTO>? = null

    @JsonProperty("credits")
     var credits: CreditosDTO? = null

     var aggregateWatchProvider: AggregateWatchProvider? = null
}
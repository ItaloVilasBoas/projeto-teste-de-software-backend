package com.movielist.external.client

import com.movielist.external.dto.*
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(value = "tmdb", url = "https://api.themoviedb.org/3")
interface IntegracaoTmdbClient {
    @GetMapping("/search/company")
    fun searchCompany(
        @RequestHeader("Authorization") authorization: String?,
        @RequestParam("query") query: String?,
        @RequestParam("page") page: Int?
    ): TmdbCabecalhoDTO<TmdbEmpresaDTO>

    @GetMapping("/search/movie")
    fun searchMovie(
        @RequestHeader("Authorization") authorization: String?,
        @RequestParam("query") query: String?,
        @RequestParam("include_adult") includeAdult: Boolean?,
        @RequestParam("language") language: String?,
        @RequestParam("page") page: Int?
    ): TmdbCabecalhoDTO<TmdbFilmeSerieDTO>

    @GetMapping("/search/tv")
    fun searchTv(
        @RequestHeader("Authorization") authorization: String?,
        @RequestParam("query") query: String?,
        @RequestParam("include_adult") includeAdult: Boolean?,
        @RequestParam("language") language: String?,
        @RequestParam("page") page: Int?
    ): TmdbCabecalhoDTO<TmdbFilmeSerieDTO>

    @GetMapping("/search/person")
    fun searchPerson(
        @RequestHeader("Authorization") authorization: String?,
        @RequestParam("query") query: String?,
        @RequestParam("include_adult") includeAdult: Boolean?,
        @RequestParam("language") language: String?,
        @RequestParam("page") page: Int?
    ): TmdbCabecalhoDTO<TmdbPessoaDTO>

    @GetMapping("/movie/{movieId}")
    fun findDetailsMovie(
        @RequestHeader("Authorization") authorization: String?,
        @RequestParam("append_to_response") appendToResponse: String?,
        @RequestParam("language") language: String?,
        @PathVariable movieId: Long?
    ): TmdbDetalheFilmeDTO

    @GetMapping("/movie/{movieId}/watch/providers")
    fun findWatchProviders(
        @RequestHeader("Authorization") authorization: String?,
        @PathVariable movieId: Long?
    ): TmdbWatchProvidersDTO

    @GetMapping("/movie/popular")
    fun findPopular(
        @RequestHeader("Authorization") authorization: String?,
        @RequestParam("language") language: String?,
        @RequestParam("page") page: Int?
    ): TmdbCabecalhoDTO<TmdbDetalheFilmeDTO>

    @GetMapping("/movie/{movieId}/credits")
    fun findCreditsMovie(
        @RequestHeader("Authorization") authorization: String?,
        @RequestParam("language") language: String?,
        @PathVariable movieId: Long?
    ): CreditosDTO

    @GetMapping("/movie/{movieId}/videos")
    fun findVideosMovie(
        @RequestHeader("Authorization") authorization: String?,
        @RequestParam("language") language: String?,
        @PathVariable movieId: Long?
    ): TmdbCabecalhoDTO<VideoDTO>

    //@GetMapping("/movie/{movieId}/watch/providers")
    //TODO: findWhereToWatchMovie(@RequestHeader("Authorization") String authorization, @PathVariable Long movieId);

    //@GetMapping("/movie/{movieId}/watch/providers")
    //TODO: findWhereToWatchMovie(@RequestHeader("Authorization") String authorization, @PathVariable Long movieId);

    @GetMapping("/tv/{seriesId}")
    fun findDetailsTv(
        @RequestHeader("Authorization") authorization: String?,
        @RequestParam("append_to_response") appendToResponse: String?,
        @RequestParam("language") language: String?,
        @PathVariable seriesId: Long?
    ): TmdbDetalheSerieDTO

    @GetMapping("/person/{personId}")
    fun findDetailsPerson(
        @RequestHeader("Authorization") authorization: String?,
        @RequestParam("append_to_response") appendToResponse: String?,
        @RequestParam("language") language: String?,
        @PathVariable personId: Long?
    ): TmdbDetalhePessoaDTO

    @GetMapping("/company/{companyId}")
    fun findDetailsCompany(
        @RequestHeader("Authorization") authorization: String?,
        @PathVariable companyId: Long?
    ): TmdbDetalheEmpresaDTO
}
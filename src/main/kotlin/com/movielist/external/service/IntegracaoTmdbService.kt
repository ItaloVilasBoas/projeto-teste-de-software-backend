package com.movielist.external.service

import com.movielist.external.client.IntegracaoTmdbClient
import com.movielist.external.dto.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service


@Service
class IntegracaoTmdbService(val tmdbClient: IntegracaoTmdbClient) {
    @Value("\${rest.tmdb.token}")
    private lateinit var tokenAuth: String
    private val BEARER = "Bearer "

    fun mostrarFilmesPopulares(): List<TmdbDetalheFilmeDTO> {
        return tmdbClient.findPopular(BEARER + tokenAuth, "pt-BR", 1).getResults()
    }

    fun buscarEmpresa(busca: String?): TmdbCabecalhoDTO<TmdbEmpresaDTO> {
        return tmdbClient.searchCompany(BEARER + tokenAuth, busca, 1)
    }

    fun buscarFilme(busca: String?): TmdbCabecalhoDTO<TmdbFilmeSerieDTO> {
        return tmdbClient.searchMovie(BEARER + tokenAuth, busca, false, "pt-BR", 1)
    }

    fun buscarSerie(busca: String?): TmdbCabecalhoDTO<TmdbFilmeSerieDTO> {
        return tmdbClient.searchTv(BEARER + tokenAuth, busca, false, "pt-BR", 1)
    }

    fun buscarPessoa(busca: String?): TmdbCabecalhoDTO<TmdbPessoaDTO> {
        return tmdbClient.searchPerson(BEARER + tokenAuth, busca, false, "pt-BR", 1)
    }

    fun detalharFilme(id: Long?): TmdbDetalheFilmeDTO {
        val filme: TmdbDetalheFilmeDTO = tmdbClient.findDetailsMovie(BEARER + tokenAuth, "", "pt-BR", id)
        val watchProviders =  tmdbClient.findWatchProviders(BEARER + tokenAuth, id)
        filme.videos = tmdbClient.findVideosMovie(BEARER + tokenAuth, "", id)
        filme.credits = tmdbClient.findCreditsMovie(BEARER + tokenAuth, "pt-BR", id)
        if(watchProviders.results.containsKey("BR"))
            filme.aggregateWatchProvider = watchProviders.results["BR"]
        return filme
    }

    fun detalharSerie(id: Long?): TmdbDetalheSerieDTO {
        return tmdbClient.findDetailsTv(BEARER + tokenAuth, "", "pt-BR", id)
    }

    fun detalharPessoa(id: Long?): TmdbDetalhePessoaDTO {
        return tmdbClient.findDetailsPerson(BEARER + tokenAuth, "", "pt-BR", id)
    }

    fun detalharEmpresa(id: Long?): TmdbDetalheEmpresaDTO {
        return tmdbClient.findDetailsCompany(BEARER + tokenAuth, id)
    }
}
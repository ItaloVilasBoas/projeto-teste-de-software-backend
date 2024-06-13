package com.movielist.core.model.dto.response

import com.movielist.external.dto.AggregateWatchProvider
import com.movielist.core.model.dto.response.CardPessoaResponseDTO
import com.movielist.external.dto.GenreDTO
import com.movielist.external.dto.TmdbDetalheFilmeDTO


class ExibirFilmeResponseDTO{
    val id: Long
    val urlCapa: String?
    val urlImagemFundo: String?
    val titulo: String?
    val descricao: String?
    val generos: List<String>?
    val dataLancamento: String?
    val tituloOriginal: String?
    val situacao: String?
    val idiomaOriginal: String?
    val diretoresEscritores: List<CardPessoaResponseDTO>
    val elencoPrincipal: List<CardPessoaResponseDTO>
    val assistaEm: AggregateWatchProvider?
    val duracao: String
    val trailers: List<String>

    constructor(filmeDTO: TmdbDetalheFilmeDTO){
        this.id = filmeDTO.id!!
        this.urlCapa = "https://image.tmdb.org/t/p/original" + filmeDTO.posterPath
        this.urlImagemFundo = "https://image.tmdb.org/t/p/original" + filmeDTO.backdropPath
        this.titulo = filmeDTO.title
        this.descricao = filmeDTO.overview
        this.generos = filmeDTO.genres.map(GenreDTO::getName).toList()
        this.dataLancamento = filmeDTO.releaseDate
        this.tituloOriginal = filmeDTO.originalTitle
        this.situacao = filmeDTO.status
        this.idiomaOriginal = filmeDTO.originalLanguage
        filmeDTO.aggregateWatchProvider?.buy?.forEach{ it.logo_path =  "https://image.tmdb.org/t/p/original${it.logo_path}" }
        filmeDTO.aggregateWatchProvider?.rent?.forEach{ it.logo_path =  "https://image.tmdb.org/t/p/original${it.logo_path}" }
        filmeDTO.aggregateWatchProvider?.flatrate?.forEach{ it.logo_path =  "https://image.tmdb.org/t/p/original${it.logo_path}" }
        this.assistaEm = filmeDTO.aggregateWatchProvider
        this.duracao = filmeDTO.runtime?.let { runtime -> "${runtime / 60}h${runtime % 60}m" } ?: "Not Found"
        this.diretoresEscritores = filmeDTO.credits?.crew?.stream()
            ?.filter { it.job in setOf("Director", "Writer", "Screenplay") }
            ?.map(::CardPessoaResponseDTO)
            ?.toList()
            ?: listOf()
        this.elencoPrincipal = filmeDTO.credits?.cast?.stream()?.limit(10)
            ?.map(::CardPessoaResponseDTO)
            ?.toList()
            ?: listOf()
        this.trailers = filmeDTO.videos?.results?.stream()
            ?.filter{ it.site.equals("YouTube") && it.type.equals("Trailer") }
            ?.map{ "https://www.youtube.com/watch?v=" + it.key }
            ?.toList()
            ?: listOf()
    }
}
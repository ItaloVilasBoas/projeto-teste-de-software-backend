package com.movielist.core.model.dto.response

import com.movielist.external.dto.TmdbFilmeSerieDTO

class CardFilmeOuSerieResponseDTO : CardDefaultResponse{
    val titulo: String
    val descricao: String

    constructor(tmdbFilmeSerieDTO: TmdbFilmeSerieDTO) : super(tmdbFilmeSerieDTO.id, tmdbFilmeSerieDTO.posterPath){
        this.titulo = tmdbFilmeSerieDTO?.title ?: tmdbFilmeSerieDTO.name
        this.descricao = tmdbFilmeSerieDTO.overview
    }
}
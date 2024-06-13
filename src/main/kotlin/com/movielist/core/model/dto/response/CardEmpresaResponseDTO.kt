package com.movielist.core.model.dto.response

import com.movielist.external.dto.TmdbEmpresaDTO

class CardEmpresaResponseDTO : CardDefaultResponse{
    private val nome: String

    constructor(empresaDTO: TmdbEmpresaDTO) : super(empresaDTO.id, empresaDTO.logoPath){
        this.nome = empresaDTO.name
    }
}
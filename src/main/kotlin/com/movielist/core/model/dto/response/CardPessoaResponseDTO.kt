package com.movielist.core.model.dto.response

import com.movielist.external.dto.TmdbElencoDTO
import com.movielist.external.dto.TmdbPessoaDTO

class CardPessoaResponseDTO : CardDefaultResponse{
    val nome: String
    val cargo: String
    val personagem: String?

    constructor(pessoaDTO: TmdbPessoaDTO) : super(pessoaDTO.id, pessoaDTO.profilePath){
        this.nome = pessoaDTO.name
        this.cargo = pessoaDTO.knownForDepartment
        this.personagem = null
    }

    constructor(elencoDTO: TmdbElencoDTO) : super(elencoDTO.id, elencoDTO.profilePath){
        this.nome = elencoDTO.name
        this.cargo = elencoDTO.job ?: elencoDTO.knownForDepartment.replace("ing", "or")
        this.personagem = elencoDTO.character
    }
}

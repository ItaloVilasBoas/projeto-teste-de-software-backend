package com.movielist.core.model.dto.response

class CardMultiBuscaResponseDTO {
    var pessoas: List<CardPessoaResponseDTO> = ArrayList()
    var series: List<CardFilmeOuSerieResponseDTO> = ArrayList()
    var filmes: List<CardFilmeOuSerieResponseDTO> = ArrayList()
    var empresas: List<CardEmpresaResponseDTO> = ArrayList()
}
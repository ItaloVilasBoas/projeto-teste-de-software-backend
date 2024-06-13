package com.movielist.core.service

import com.movielist.core.model.dto.response.*
import com.movielist.external.service.IntegracaoTmdbService
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class ConexaoTmdbService(val tmdbService: IntegracaoTmdbService) {
    fun buscarCardFilmePessoaSerie(busca: String?): CardMultiBuscaResponseDTO {
        val resultadoBusca = CardMultiBuscaResponseDTO()
        resultadoBusca.filmes = tmdbService.buscarFilme(busca).results.stream().map(::CardFilmeOuSerieResponseDTO)
            .toList()
        resultadoBusca.series = tmdbService.buscarSerie(busca).results.stream().map(::CardFilmeOuSerieResponseDTO)
            .toList()
//        resultadoBusca.pessoas = tmdbService.buscarPessoa(busca).results.stream().map(::CardPessoaResponseDTO)
//            .toList()
//        resultadoBusca.empresas = tmdbService.buscarEmpresa(busca).results.stream().map(::CardEmpresaResponseDTO)
//            .toList()
        return resultadoBusca
    }

    fun buscarFilmesPopulares(): List<ExibirFilmeResponseDTO> {
        return tmdbService.mostrarFilmesPopulares().stream().map(::ExibirFilmeResponseDTO).toList()
    }

    fun buscarCardFilmeTmdb(busca: String?): List<CardFilmeOuSerieResponseDTO> {
        return tmdbService.buscarFilme(busca).results.stream().map(::CardFilmeOuSerieResponseDTO)
            .collect(Collectors.toList())
    }

    fun buscarCardSerieTmdb(busca: String?): List<CardFilmeOuSerieResponseDTO> {
        return tmdbService.buscarSerie(busca).results.stream().map(::CardFilmeOuSerieResponseDTO)
            .collect(Collectors.toList())
    }

    fun buscarCardPessoaTmdb(busca: String?): List<CardPessoaResponseDTO> {
        return tmdbService.buscarPessoa(busca).results.stream().map(::CardPessoaResponseDTO)
            .collect(Collectors.toList())
    }

    fun buscarEmpresaTmdb(busca: String?): List<CardEmpresaResponseDTO> {
        return tmdbService.buscarEmpresa(busca).results.stream().map(::CardEmpresaResponseDTO)
            .collect(Collectors.toList())
    }

    fun detalharFilme(id: Long?): ExibirFilmeResponseDTO {
        return ExibirFilmeResponseDTO(tmdbService.detalharFilme(id))
    }
}

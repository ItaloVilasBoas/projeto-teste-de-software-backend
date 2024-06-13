package com.movielist.core.controller

import com.movielist.core.model.dto.response.CardFilmeOuSerieResponseDTO
import com.movielist.core.model.dto.response.CardMultiBuscaResponseDTO
import com.movielist.core.model.dto.response.CardPessoaResponseDTO
import com.movielist.core.model.dto.response.ExibirFilmeResponseDTO
import com.movielist.core.service.ConexaoTmdbService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("card")
@CrossOrigin(origins = ["*"], maxAge = 36)
class CardController(val conexaoTmdbService: ConexaoTmdbService) {
    @GetMapping("/populares")
    fun mostrarPopulares(): List<ExibirFilmeResponseDTO> {
        return conexaoTmdbService.buscarFilmesPopulares()
    }

    @GetMapping("")
    fun pesquisarFilmeSeriePessoa(@RequestParam nome: String?): CardMultiBuscaResponseDTO {
        return conexaoTmdbService.buscarCardFilmePessoaSerie(nome)
    }

    @GetMapping("/filme")
    fun pesquisarFilme(@RequestParam nome: String?): List<CardFilmeOuSerieResponseDTO> {
        return conexaoTmdbService.buscarCardFilmeTmdb(nome)
    }

    @GetMapping("/serie")
    fun pesquisarSerie(@RequestParam nome: String?): List<CardFilmeOuSerieResponseDTO> {
        return conexaoTmdbService.buscarCardSerieTmdb(nome)
    }

    @GetMapping("/pessoa")
    fun pesquisarPessoa(@RequestParam nome: String?): List<CardPessoaResponseDTO> {
        return conexaoTmdbService.buscarCardPessoaTmdb(nome)
    }

    @GetMapping("/filme/{idFilme}")
    fun detalharFilme(@PathVariable idFilme: Long?): ExibirFilmeResponseDTO {
        return conexaoTmdbService.detalharFilme(idFilme)
    }

    @GetMapping("/serie/{idSerie}")
    fun detalharSerie(@PathVariable idSerie: Long?): List<CardPessoaResponseDTO> {
        return ArrayList<CardPessoaResponseDTO>()
    }

    @GetMapping("/pessoa/{idPessoa}")
    fun detalharPessoa(@PathVariable idPessoa: Long?): List<CardPessoaResponseDTO> {
        return ArrayList<CardPessoaResponseDTO>()
    }
}
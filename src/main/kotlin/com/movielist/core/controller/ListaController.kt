package com.movielist.core.controller

import com.movielist.core.model.dto.request.AdicionarComentarioRequest
import com.movielist.core.model.dto.request.MovieListRequestDTO
import com.movielist.core.model.dto.response.ListaPopularResponseDTO
import com.movielist.core.model.entity.ItemFilme
import com.movielist.core.model.entity.MovieList
import com.movielist.core.service.ListaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("lista")
@CrossOrigin(origins = ["*"], maxAge = 36)
class ListaController(val listaService: ListaService) {
    @GetMapping("")
    fun buscarListasMovieList(@RequestParam listas: List<Long>): ResponseEntity<List<MovieList>> {
        return listaService.buscarListasMovieList(listas)
    }

    @GetMapping("/populares")
    fun buscarListasMovieListPopulares(): ResponseEntity<List<ListaPopularResponseDTO>> {
        return listaService.buscarListasMovieListPopulares()
    }

    @GetMapping("/{idLista}")
    fun buscarMovieList(@PathVariable idLista: Long): ResponseEntity<MovieList> {
        return listaService.buscarMovieList(idLista)
    }

    @PostMapping("")
    fun adicionarMovieList(@RequestBody movieList: MovieListRequestDTO, @RequestHeader token: String): ResponseEntity<String> {
        return listaService.criarLista(movieList, token)
    }

    @PostMapping("/usuario/{idUsuario}")
    fun adicionarOuAtualizarFilmeListaGeral(@PathVariable idUsuario: Long,
                             @RequestBody filmeRequest: ItemFilme,
                             @RequestHeader token: String): ResponseEntity<String> {
        return listaService.adicionarOuAtualizarFilme(idUsuario, filmeRequest, token)
    }

    @GetMapping("/mock/{id}")
    fun gerarMock(@PathVariable id: Long): ResponseEntity<String> {
        return listaService.gerarMockListaFilmes(id)
    }

    @DeleteMapping("/usuario/{idUsuario}/filme/{idFilme}")
    fun removerFilmeListaGeral(@PathVariable idUsuario: Long,
                     @PathVariable idFilme: Long,
                     @RequestHeader token: String): ResponseEntity<String> {
        return listaService.removerFilme(idUsuario, idFilme, token)
    }

    @PutMapping("/{idLista}")
    fun atualizarMovieList(@PathVariable idLista: Long,
                           @RequestBody movieList: MovieListRequestDTO,
                           @RequestHeader token: String): ResponseEntity<String> {
        return listaService.atualizarLista(idLista, movieList, token)
    }

    @PutMapping("/likes/{idLista}")
    fun adicionarLikeNaLista(@PathVariable idLista: Long, @RequestParam perfil: Long): ResponseEntity<String> {
        return listaService.adicionarOuRemoverLike(idLista, perfil)
    }

    @PutMapping("/comentario/{idLista}")
    fun adicionarComentarioNaLista(@PathVariable idLista: Long, @RequestBody comentarioRequest: AdicionarComentarioRequest): ResponseEntity<String> {
        return listaService.adicionarComentario(idLista, comentarioRequest.idPerfil, comentarioRequest.comentario)
    }

    @DeleteMapping("/{idLista}")
    fun deletarLista(@PathVariable idLista: Long, @RequestHeader token: String): ResponseEntity<String> {
        return listaService.removerLista(idLista, token)
    }
}
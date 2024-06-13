package com.movielist.core.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.movielist.core.exception.NotAuthorizedException
import com.movielist.core.exception.NotFoundException
import com.movielist.core.model.dto.request.AlterarPerfilRequestDTO
import com.movielist.core.model.dto.response.PerfilResponseDTO
import com.movielist.core.repository.ListaRepositoy
import com.movielist.core.repository.PerfilRepository
import com.movielist.core.repository.TokenUsuarioRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class PerfilService(private val perfilRepository: PerfilRepository,
                    private val listaRepositoy: ListaRepositoy,
                    private val tokenUsuarioRepository: TokenUsuarioRepository,
                    private val tokenService: TokenService) {
    fun buscarPerfil(idPerfil: Long): ResponseEntity<PerfilResponseDTO>{
        val perfil = perfilRepository.findById(idPerfil)
            .orElseThrow{ NotFoundException("Perfil $idPerfil não Encontrado") }
        val listasMovieList = listaRepositoy.findByIdUsuario(perfil.id)
            .stream().map { Pair(it.id!!, it.getData()) }.toList()
        return ResponseEntity.status(HttpStatus.OK).body(PerfilResponseDTO(perfil, listasMovieList))
    }

    fun buscarPerfilPorNome(nomeUsuario: String): ResponseEntity<PerfilResponseDTO> {
        val perfil = perfilRepository.findByNomeUsuario(nomeUsuario)
            .orElseThrow{ NotFoundException("Perfil não encontrado") }
        val listasMovieList = listaRepositoy.findByIdUsuario(perfil.id)
            .stream().map { Pair(it.id!!, it.getData()) }.toList()
        return ResponseEntity.status(HttpStatus.OK).body(PerfilResponseDTO(perfil, listasMovieList))
    }

    fun buscarPerfilPorToken(token: String): ResponseEntity<PerfilResponseDTO>{
        val idPerfil = tokenUsuarioRepository.findByValor(token).map { it.idUsuario }
            .orElseThrow{ NotFoundException("Não possivel identificar um perfil ativo com o token informado") }
        return buscarPerfil(idPerfil)
    }

    fun editarPerfil(idPerfil: Long, novoPerfil: AlterarPerfilRequestDTO, token: String): ResponseEntity<String>{
        if(!tokenService.validarToken(token, idPerfil))
            throw NotAuthorizedException("Você não tem permissão para alterar esse perfil!")
        val perfil = perfilRepository.findById(idPerfil)
            .orElseThrow{ NotFoundException("Perfil $idPerfil não Encontrado") }
        perfil.nomeUsuario = novoPerfil.nomeUsuario
        perfil.foto = novoPerfil.fotoPerfil
        perfil.header = novoPerfil.headerPerfil
        val novoData = perfil.getData()
        novoData.rede = novoPerfil.rede
        perfil.data = ObjectMapper().writeValueAsBytes(novoData)
        perfilRepository.save(perfil)
        return ResponseEntity.status(HttpStatus.OK).body("Perfil editado com sucesso!")
    }
}
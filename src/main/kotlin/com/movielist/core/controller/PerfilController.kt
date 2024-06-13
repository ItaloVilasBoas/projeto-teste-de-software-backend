package com.movielist.core.controller

import com.movielist.core.model.dto.request.AlterarPerfilRequestDTO
import com.movielist.core.model.dto.response.PerfilResponseDTO
import com.movielist.core.service.PerfilService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("perfil")
@CrossOrigin(origins = ["*"], maxAge = 36)
class PerfilController(val perfilService: PerfilService){
    @GetMapping("/{nomeUsuario}")
    fun buscarPerfil(@PathVariable nomeUsuario: String):  ResponseEntity<PerfilResponseDTO> {
        return perfilService.buscarPerfilPorNome(nomeUsuario)
    }

    @GetMapping("")
    fun buscarPerfilToken(@RequestHeader token: String): ResponseEntity<PerfilResponseDTO> {
        return perfilService.buscarPerfilPorToken(token)
    }

    @PutMapping("/{idPerfil}")
    fun editarPerfil(@PathVariable idPerfil: Long,
                     @RequestBody novoPerfil: AlterarPerfilRequestDTO,
                     @RequestHeader token: String):  ResponseEntity<String> {
        return perfilService.editarPerfil(idPerfil, novoPerfil, token)
    }
}
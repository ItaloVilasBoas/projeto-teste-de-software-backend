package com.movielist.core.controller

import com.movielist.core.model.dto.request.AlterarSenhaRequestDTO
import com.movielist.core.model.dto.request.UsuarioRequestDTO
import com.movielist.core.service.UsuarioService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("usuario")
@CrossOrigin(origins = ["*"], maxAge = 36)
class UsuarioController(val usuarioService: UsuarioService){
    @PostMapping("")
    fun criarUsuario(@RequestBody usuario: UsuarioRequestDTO): ResponseEntity<String> {
        return usuarioService.criarUsuario(usuario)
    }

    @PutMapping("/{idUsuario}")
    fun atualizarSenhaUsuario(@PathVariable idUsuario: Long,
                              @RequestBody alterarSenhaBody: AlterarSenhaRequestDTO,
                              @RequestHeader token: String): ResponseEntity<String> {
        return usuarioService.atualizarSenha(idUsuario, alterarSenhaBody, token)
    }

    @DeleteMapping("/{idUsuario}")
    fun removerUsuario(@PathVariable idUsuario: Long, @RequestHeader token: String): ResponseEntity<String> {
        return usuarioService.removerUsuario(idUsuario, token)
    }

    @GetMapping("/login")
    fun validarLogin(@RequestHeader email: String, @RequestHeader senha: String): ResponseEntity<String> {
        return usuarioService.validarLogin(email, senha)
    }
}
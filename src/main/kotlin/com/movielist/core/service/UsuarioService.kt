package com.movielist.core.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.movielist.core.exception.NotAuthorizedException
import com.movielist.core.exception.NotFoundException
import com.movielist.core.exception.RegraNegocialNaoAtendidaException
import com.movielist.core.model.dto.request.AlterarSenhaRequestDTO
import com.movielist.core.model.dto.request.UsuarioRequestDTO
import com.movielist.core.model.entity.DetailedProfile
import com.movielist.core.model.entity.Perfil
import com.movielist.core.model.entity.Usuario
import com.movielist.core.repository.PerfilRepository
import com.movielist.core.repository.TokenUsuarioRepository
import com.movielist.core.repository.UsuarioRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service

@Service
class UsuarioService(private val usuarioRepository: UsuarioRepository,
                     private val perfilRepository: PerfilRepository,
                     private val tokenUsuarioRepository: TokenUsuarioRepository,
                     private val tokenService: TokenService) {
    fun criarUsuario(usuarioRequest: UsuarioRequestDTO): ResponseEntity<String> {
        if(!usuarioRepository.findByEmail(usuarioRequest.email).isEmpty)
            throw RegraNegocialNaoAtendidaException("Já existe um usuário registrado nesse Email!")
        if(!perfilRepository.findByNomeUsuario(usuarioRequest.nome).isEmpty)
            throw RegraNegocialNaoAtendidaException("Nome de usuário já registrado!")

        val senhaCriptografada = criptografarSenha(usuarioRequest.senha)
        val usuario = usuarioRepository.save(Usuario(usuarioRequest.email, senhaCriptografada))
        val novoPerfil = Perfil(usuario.id, usuarioRequest.nome, DetailedProfile())
        perfilRepository.save(novoPerfil)
        return ResponseEntity.status(HttpStatus.OK).body("Usuario registrado com sucesso!")
    }

    fun atualizarSenha(idUsuario: Long, reqAlterarSenha: AlterarSenhaRequestDTO, token: String): ResponseEntity<String> {
        if(!tokenService.validarToken(token, idUsuario))
            throw NotAuthorizedException("Você não tem permissão para alterar a senha do usuario!")
        val usuario = usuarioRepository.findById(idUsuario)
            .orElseThrow{ NotFoundException("Usuário: $idUsuario não encontrado") }
        if(!BCrypt.checkpw(reqAlterarSenha.senhaAntiga, usuario.senha) )
            throw RegraNegocialNaoAtendidaException("Sua senha antiga está incorreta")

        usuario.senha = criptografarSenha(reqAlterarSenha.senhaNova)
        usuarioRepository.save(usuario)
        return ResponseEntity.status(HttpStatus.OK).body("Senha do usuario atualizada com sucesso!")
    }

    fun removerUsuario(idUsuario: Long, token: String) : ResponseEntity<String> {
        if(!tokenService.validarToken(token, idUsuario))
            throw NotAuthorizedException("Você não tem permissão para excluir o usuario!")
        val usuario = usuarioRepository.findById(idUsuario)
            .orElseThrow { NotFoundException("Usuario: $idUsuario não encontrado") }

        usuarioRepository.delete(usuario)
        perfilRepository.deleteById(usuario.id)
        tokenUsuarioRepository.removeByIdUsuario(idUsuario)
        return ResponseEntity.status(HttpStatus.OK).body("Usuario removido com sucesso!")
    }

    fun validarLogin(email: String, senha: String): ResponseEntity<String> {
        val usuario = usuarioRepository.findByEmail(email)
            .orElseThrow{ NotAuthorizedException("O email ou senha inseridos estão incorretos. Por favor, verifique suas credenciais e tente novamente.") }
        if(!BCrypt.checkpw(senha, usuario.senha))
            throw NotAuthorizedException("O email ou senha inseridos estão incorretos. Por favor, verifique suas credenciais e tente novamente..")

        return ResponseEntity.status(HttpStatus.OK).body(tokenService.criarToken(usuario.id))
    }

    fun criptografarSenha(senha: String): String {
        return BCrypt.hashpw(senha, BCrypt.gensalt())
    }
}
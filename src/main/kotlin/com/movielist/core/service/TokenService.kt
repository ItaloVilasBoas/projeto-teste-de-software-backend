package com.movielist.core.service

import com.movielist.core.model.entity.TokenUsuario
import com.movielist.core.repository.TokenUsuarioRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class TokenService(private val tokenUsuarioRepository: TokenUsuarioRepository) {
    fun criarToken(idUsuario: Long) : String {
        return tokenUsuarioRepository
            .save(TokenUsuario(UUID.randomUUID().toString(), LocalDate.now().plusWeeks(1), idUsuario))
            .valor
    }

    fun deletarToken(token: String) {
        tokenUsuarioRepository.findByValor(token)
            .ifPresent { tokenUsuarioRepository.delete(it) }
    }

    fun validarToken(token: String, idUsuario: Long) : Boolean {
        return tokenUsuarioRepository.findByValor(token)
            .map { it.idUsuario == idUsuario && it.expiresIn.isAfter(LocalDate.now()) }
            .orElseGet{ false }
    }
}
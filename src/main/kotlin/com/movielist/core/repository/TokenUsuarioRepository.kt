package com.movielist.core.repository

import com.movielist.core.model.entity.TokenUsuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional
import java.util.Optional

interface TokenUsuarioRepository : JpaRepository<TokenUsuario, Long> {
    fun findByValor(valor: String): Optional<TokenUsuario>
    @Transactional
    fun removeByIdUsuario(idUsuario: Long): List<TokenUsuario>
}
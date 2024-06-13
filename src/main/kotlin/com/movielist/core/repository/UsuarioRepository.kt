package com.movielist.core.repository

import com.movielist.core.model.entity.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UsuarioRepository : JpaRepository<Usuario, Long>{
    fun findByEmail(email: String): Optional<Usuario>
}
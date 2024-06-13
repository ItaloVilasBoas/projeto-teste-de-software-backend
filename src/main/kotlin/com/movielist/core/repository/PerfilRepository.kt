package com.movielist.core.repository

import com.movielist.core.model.entity.Perfil
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PerfilRepository : JpaRepository<Perfil, Long>{
    fun findByNomeUsuario(nome: String) : Optional<Perfil>
}
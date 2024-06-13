package com.movielist.core.repository

import com.movielist.core.model.entity.Lista
import org.springframework.data.jpa.repository.JpaRepository

interface ListaRepositoy: JpaRepository<Lista, Long> {
    fun findByIdUsuario(idUsuario: Long): List<Lista>
}
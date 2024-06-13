package com.movielist.core.model.entity

import com.movielist.core.model.dto.request.UsuarioRequestDTO
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Usuario (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID_USUARIO")
        var id: Long,
        @Column(name = "EMAIL")
        var email: String,
        @Column(name = "SENHA")
        var senha: String,
    ){
    constructor(email: String, senha: String): this(Long.MIN_VALUE, email, senha){
        this.id = Long.MIN_VALUE
        this.email = email
        this.senha = senha
    }
}
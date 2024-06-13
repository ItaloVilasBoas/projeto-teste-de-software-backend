package com.movielist.core.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.LocalDate

@Entity(name = "TOKEN_USUARIO")
class TokenUsuario(
    @Id
    @Column(name = "VALOR_TOKEN")
    var valor: String,
    @Column(name = "DATA_VALIDADE")
    var expiresIn: LocalDate,
    @Column(name = "ID_USUARIO")
    var idUsuario: Long
) {
}
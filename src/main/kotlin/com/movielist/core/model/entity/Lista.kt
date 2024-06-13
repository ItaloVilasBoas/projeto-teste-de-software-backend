package com.movielist.core.model.entity

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Lista (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LISTA")
    var id: Long?,
    @Column(name = "ID_USUARIO")
    var idUsuario: Long,
    @Column(name = "LISTA")
    var data: ByteArray
) {
    constructor(idPerfil: Long, data: MovieList) : this(null, idPerfil, ObjectMapper().writeValueAsBytes(data))

    fun getData(): MovieList{
        return ObjectMapper().readValue(data, MovieList::class.java)
    }
}

data class MovieList(var titulo: String, var descricao: String, var itens: List<ItemMovieList>,
                     var comentarios: MutableList<Comentario>, var likes: MutableList<Like>) {
    constructor(): this("", "", ArrayList(), ArrayList(), ArrayList())
}
data class Comentario(var comentario: String, var idUsuario: Long) {
    constructor(): this("", Long.MIN_VALUE)
}
data class Like(var idUsuario: Long) {
    constructor(): this(Long.MIN_VALUE)
}
data class ItemMovieList(var idTmdb: Long, var nomeTmdb: String, var urlImage: String) {
    constructor(): this(Long.MIN_VALUE, "", "")
}

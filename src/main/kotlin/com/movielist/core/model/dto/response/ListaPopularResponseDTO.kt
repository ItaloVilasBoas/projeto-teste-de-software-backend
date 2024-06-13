package com.movielist.core.model.dto.response

import com.movielist.core.model.entity.ItemMovieList

class ListaPopularResponseDTO (
    val idLista: Long,
    val nomeLista: String,
    val idUsuario: Long,
    val fotoUsuario: String,
    val nomeUsuario: String,
    val quantidadeLikesLista: Int,
    val quantidadeComentariosLista: Int,
    val descricaoLista: String,
    val itens: List<ItemMovieList>
)
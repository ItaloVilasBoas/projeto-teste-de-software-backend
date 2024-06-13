package com.movielist.core.model.dto.request

class AlterarPerfilRequestDTO(
    var nomeUsuario: String,
    var fotoPerfil: String?,
    var headerPerfil: String?,
    var listaMovieList: List<Long>,
    var rede: List<Long>
)
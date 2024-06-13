package com.movielist.core.model.dto.response

import com.movielist.core.model.entity.Activity
import com.movielist.core.model.entity.ItemFilme
import com.movielist.core.model.entity.MovieList
import com.movielist.core.model.entity.Perfil

class PerfilResponseDTO(
    var idPerfil: Long,
    var nomeUsuario: String,
    var fotoPerfil: String?,
    var headerPerfil: String?,
    var listaAtividades: List<Activity>,
    var listaFilmes: List<ItemFilme>,
    var listaMovieList: List<Pair<Long, MovieList>>,
    var rede: List<Long>
) {
    constructor(perfil: Perfil, listaMovieList: List<Pair<Long, MovieList>>):
            this(perfil.id, perfil.nomeUsuario, perfil.foto, perfil.header, ArrayList(), ArrayList(), listaMovieList, ArrayList()) {
        val perfilDetalhes = perfil.getData()
        this.listaFilmes = perfilDetalhes.listaFilmes
        this.listaAtividades = perfilDetalhes.listaAtividades
        this.rede = perfilDetalhes.rede
    }
}
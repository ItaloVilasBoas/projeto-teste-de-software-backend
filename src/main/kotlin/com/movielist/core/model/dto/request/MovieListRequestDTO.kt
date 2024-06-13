package com.movielist.core.model.dto.request

import com.movielist.core.model.entity.ItemMovieList

class MovieListRequestDTO (var nomeMovieList: String, var descricao: String, var itens: List<ItemMovieList>, var idPerfil: Long)
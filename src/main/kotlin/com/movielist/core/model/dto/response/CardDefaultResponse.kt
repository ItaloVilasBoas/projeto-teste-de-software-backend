package com.movielist.core.model.dto.response

open class CardDefaultResponse {
    val id: Long
    val urlImagem: String

    constructor(id: Long, imagePath: String?){
        this.id = id
        this.urlImagem = setUrlImagem(imagePath)
    }

    private fun setUrlImagem(imagemPath: String?): String {
        if(imagemPath == null)
            return "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/No-Image-Placeholder.svg/1665px-No-Image-Placeholder.svg.png"
        return "https://image.tmdb.org/t/p/original${imagemPath}"
    }
}
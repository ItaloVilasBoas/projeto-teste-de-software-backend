package com.movielist.core.model.entity

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.Date

@Entity
class Perfil(
    @Id
    @Column(name = "ID_USUARIO")
    var id: Long,
    @Column(name = "NOME_USUARIO")
    var nomeUsuario: String,
    @Column(name = "PERFIL")
    var data: ByteArray,
    @Column(name = "FOTO_PERFIL")
    var foto: String?,
    @Column(name = "HEADER_PERFIL")
    var header: String?,
) {
    constructor(id: Long, nomeUsuario: String, data: DetailedProfile) : this(id, nomeUsuario, ObjectMapper().writeValueAsBytes(data), null, null)

    fun getData(): DetailedProfile {
        return ObjectMapper().readValue(data, DetailedProfile::class.java)
    }
}

class DetailedProfile(var listaAtividades: MutableList<Activity>, var listaFilmes: MutableList<ItemFilme>, var rede: List<Long>) {
    constructor(): this(ArrayList(), ArrayList(), ArrayList())
}
class ItemFilme(var idTmdb: Long, var nomeTmdb: String, var status: String?, var score: Double?,
                var comentario: String?, var favorito: Boolean?, var urlImage: String?) {
    constructor(): this(Long.MIN_VALUE, "", "", Double.MIN_VALUE, "", false, "")
}
class Activity(var acao: String, var itemId: Long, var dataDaAcao: Date) {
    constructor(): this("", Long.MIN_VALUE, Date())
}
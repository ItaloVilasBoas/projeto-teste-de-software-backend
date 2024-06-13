package com.movielist.core.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.movielist.core.exception.NotAuthorizedException
import com.movielist.core.exception.NotFoundException
import com.movielist.core.model.dto.request.MovieListRequestDTO
import com.movielist.core.model.dto.response.ListaPopularResponseDTO
import com.movielist.core.model.entity.*
import com.movielist.core.repository.ListaRepositoy
import com.movielist.core.repository.PerfilRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.Date
import kotlin.random.Random

@Service
class ListaService(private val listaRepositoy: ListaRepositoy,
                   private val perfilRepository: PerfilRepository,
                   private val tokenService: TokenService,
                   val conexaoTmdbService: ConexaoTmdbService){
    fun buscarListasMovieList(listaId: List<Long>): ResponseEntity<List<MovieList>> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(listaRepositoy.findAllById(listaId).map{ it.getData() })
    }

    fun buscarListasMovieListPopulares(): ResponseEntity<List<ListaPopularResponseDTO>> {
        var listas = listaRepositoy.findAll()
            .map{ Triple(it.id, it.idUsuario, it.getData()) }
            .sortedWith( compareByDescending{ it.third.likes.size } )
            .stream().limit(4)
            .map {
                val usuario = perfilRepository
                    .findById(it.second)
                    .orElse(Perfil(0, "desconhecido", DetailedProfile()))
                val foto = usuario.foto ?: "https://s.ltrbxd.com/static/img/avatar1000.a71b6e9c.png"
                return@map ListaPopularResponseDTO(
                    it.first!!, it.third.titulo, it.second, foto, usuario.nomeUsuario,
                    it.third.likes.size, it.third.comentarios.size, it.third.descricao, it.third.itens)
            }
            .toList()
        return ResponseEntity.status(HttpStatus.OK).body(listas)
    }

    fun buscarMovieList(idLista: Long): ResponseEntity<MovieList> {
        val lista = listaRepositoy.findById(idLista)
            .orElseThrow{ NotFoundException("Lista com id $idLista não encontrada") }
        return ResponseEntity.status(HttpStatus.OK)
            .body(lista.getData())
    }

    fun adicionarComentario(idLista: Long, idPerfil: Long, comentario: String): ResponseEntity<String> {
        val lista = listaRepositoy.findById(idLista)
            .orElseThrow{ NotFoundException("Lista com id $idLista não encontrada") }
        val movieList = lista.getData()
        movieList.comentarios.plusAssign(Comentario(comentario, idPerfil))
        lista.data = ObjectMapper().writeValueAsBytes(movieList)
        listaRepositoy.save(lista);
        return ResponseEntity.status(HttpStatus.OK).body("Operação realizada com sucesso!")
    }

    fun adicionarOuRemoverLike(idLista: Long, idPerfil: Long): ResponseEntity<String> {
        val lista = listaRepositoy.findById(idLista)
            .orElseThrow{ NotFoundException("Lista com id $idLista não encontrada") }
        val movieList = lista.getData()
        var resultado = "removido"
        if(!movieList.likes.removeIf { it.idUsuario == idPerfil }){
            resultado = "adicionado"
            movieList.likes.plusAssign(Like(idPerfil))
        }
        lista.data = ObjectMapper().writeValueAsBytes(movieList)
        listaRepositoy.save(lista);
        return ResponseEntity.status(HttpStatus.OK).body("Like $resultado com sucesso!")
    }

    fun criarLista(requestMovieList: MovieListRequestDTO, token: String): ResponseEntity<String> {
//        if(!tokenService.validarToken(token, requestMovieList.idPerfil))
//            throw NotAuthorizedException("Você não tem permissão para criar uma lista para esse usuario!")
        val movieList = MovieList(requestMovieList.nomeMovieList, requestMovieList.descricao, requestMovieList.itens,
                                  ArrayList(), ArrayList())
        val lista = listaRepositoy.save(Lista(requestMovieList.idPerfil, movieList))

        registrarAtividade(requestMovieList.idPerfil, Activity("Criou a lista ${lista.getData().titulo}", lista.id!!, Date()))
        return ResponseEntity.status(HttpStatus.OK).body("Lista criada com sucesso!")
    }

    fun removerFilme(idPerfil: Long, idTmdb: Long, token: String): ResponseEntity<String> {
        if(!tokenService.validarToken(token, idPerfil))
            throw NotAuthorizedException("Você não tem permissão para criar uma lista para esse usuario!")
        val perfil = perfilRepository.findById(idPerfil)
            .orElseThrow{ NotFoundException("Perfil com id $idPerfil não encontrado") }

        var detailedProfile = perfil.getData()
        val filme = detailedProfile.listaFilmes.find{ it.idTmdb == idTmdb }
        if(filme != null) {
            detailedProfile.listaFilmes.remove(filme)
            registrarAtividade(perfil.id, Activity("Removeu o filme ${filme.nomeTmdb}", idTmdb, Date()))
        }
        perfil.data = ObjectMapper().writeValueAsBytes(detailedProfile)
        perfilRepository.save(perfil)

        return ResponseEntity.status(HttpStatus.OK).body("Filme removido com sucesso!")
    }

    fun gerarMockListaFilmes(idPerfil: Long): ResponseEntity<String> {
        val pesquisarFilmes = arrayOf(
            "Senhor dos Aneis", "Poderoso Chefão", "Godzilla", "Batman", "Pulp Fiction", "Clube da luta", "Matrix",
            "Goodfellas", "Interesterlar", "Star Wars", "A espera de um milagre", "Parasita", "Psicose", "Gladiador",
            "Harakiri", "Kobayashi", "Twink Peaks", "Homem Elefante", "Bastardos Inglorios", "Kill Bill",
            "Eraserhead", "Duna", "Acossado", "La chinoise" , "Sete Samurais", "Dançando no Escuro", "LalaLand", "Ran", "Rashomon",
            "Galactic Heroes", "Taxi Driver", "Princesa Kaguya", "Irlandês", "Touro Indomável", "Cure", "Homem elefante", "O menino e a garça",
            "Chihiro", "Totora", "Mononoke", "Kaijuu no Kodomo", "perfect blue", "Paranoia agent", "Memories", "Paprika", "O desprezo", "Masculino Feminino",
            "Alphaville", "Nouvelle Vague", "Hiroshima Mon Amour", "Os incompreendidos", "Doce vida", "Stalker", "Solaris", "O espelho", "O rolo compressor",
            "Anastasia", "Viver a vida", "Parasita", "Chunking Express", "Career Opportunities", "Vive Lamour", "Christmas in august", "Anjos Caídos", "Felizes Juntos",
            "Dias Selvagens", "Cães Errantes"
        )
        val loremIpsum = "Lorem ipsum dolor sit amet. " +
                "Et totam dolores aut quaerat assumenda sit odio exercitationem est sunt sunt? " +
                "Rem accusantium debitis et obcaecati galisum est deserunt voluptatibus sed asperiores similique. " +
                "Et quia voluptatibus ab veniam nobis nam voluptatem molestias qui dolor consequatur! " +
                "Et sint dolores ut quis placeat 33 consequuntur ipsa sit nihil ratione et corrupti quos."

        val listaItems = ArrayList<ItemMovieList>()
        for(i in pesquisarFilmes.indices) {
            if(i % 50 == 0) Thread.sleep(1000)
            val itens = conexaoTmdbService.buscarCardFilmeTmdb(pesquisarFilmes[i])
                .stream().map { ItemMovieList(it.id, it.titulo, it.urlImagem) }.toList()
            listaItems.plusAssign(itens)
        }

        listaItems.shuffle()
        var itensAleatorios: ArrayList<ItemMovieList>
        for(perfil in 147..199){
            itensAleatorios = ArrayList()
            for (i in 0..Random.nextInt(5, 12)) {
                if(listaItems.isEmpty())
                    break;
                itensAleatorios.plusAssign(listaItems.removeLastOrNull()!!)
            }
            if(itensAleatorios.isEmpty())
                break;
            val movieList = MovieListRequestDTO("Generated lista n. ${146-perfil}", loremIpsum, itensAleatorios, perfil.toLong())
            this.criarLista(movieList, "")
        }
        return ResponseEntity.status(HttpStatus.OK).body("Sucesso!")
    }
    fun adicionarOuAtualizarFilme(idPerfil: Long, itemFilmeRequest: ItemFilme, token: String): ResponseEntity<String> {
        if(!tokenService.validarToken(token, idPerfil))
            throw NotAuthorizedException("Você não tem permissão para atualizar a lista desse usuario!")
        val perfil = perfilRepository.findById(idPerfil)
            .orElseThrow{ NotFoundException("Perfil com id $idPerfil não encontrado") }

        var detailedProfile = perfil.getData()
        detailedProfile.listaFilmes.removeIf { it.idTmdb == itemFilmeRequest.idTmdb }
        detailedProfile.listaFilmes.plusAssign(itemFilmeRequest)
        perfil.data = ObjectMapper().writeValueAsBytes(detailedProfile)
        perfilRepository.save(perfil)

        registrarAtividade(perfil.id, Activity("Adicionou ${itemFilmeRequest.nomeTmdb} a ${itemFilmeRequest.status}",
            itemFilmeRequest.idTmdb, Date()))
        return ResponseEntity.status(HttpStatus.OK).body("Filme adicionado com sucesso!")
    }

    fun atualizarLista(idLista: Long, requestMovieList: MovieListRequestDTO, token: String): ResponseEntity<String> {
        val lista = listaRepositoy.findById(idLista)
            .orElseThrow{ NotFoundException("Lista com id $idLista não encontrada") }
        if(!tokenService.validarToken(token, lista.idUsuario))
            throw NotAuthorizedException("Você não tem permissão para alterar essa lista!")
        val movieList = lista.getData()
        movieList.titulo = requestMovieList.nomeMovieList
        movieList.descricao = requestMovieList.descricao
        movieList.itens = requestMovieList.itens
        lista.data = ObjectMapper().writeValueAsBytes(movieList)
        listaRepositoy.save(lista)
        registrarAtividade(lista.idUsuario, Activity("Atualizou a lista ${movieList.titulo}", lista.id!!, Date()))
        return ResponseEntity.status(HttpStatus.OK).body("Lista atualizada com sucesso!")
    }

    fun removerLista(idLista: Long, token: String): ResponseEntity<String> {
        val lista = listaRepositoy.findById(idLista)
            .orElseThrow{ NotFoundException("Lista com id $idLista não encontrada") }
        if(!tokenService.validarToken(token, lista.idUsuario))
            throw NotAuthorizedException("Você não tem permissão para alterar essa lista!")
        registrarAtividade(lista.idUsuario, Activity("Excluiu a lista ${lista.getData().titulo}", lista.id!!, Date()))
        listaRepositoy.delete(lista)
        return ResponseEntity.status(HttpStatus.OK).body("Lista $idLista removida com sucesso")
    }

    fun registrarAtividade(idPerfil: Long, atividade: Activity) {
        val perfil = perfilRepository.findById(idPerfil)
            .orElseThrow{ NotFoundException("Usuario com id $idPerfil não encontrada") }
        val atualizaData = perfil.getData()
        atualizaData.listaAtividades.plusAssign(atividade)
        perfil.data = ObjectMapper().writeValueAsBytes(atualizaData)
        perfilRepository.save(perfil)
    }
}
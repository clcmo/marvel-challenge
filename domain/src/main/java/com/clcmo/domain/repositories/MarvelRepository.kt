package com.clcmo.domain.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.clcmo.data.Constants
import com.clcmo.domain.entities.MarvelService
import com.clcmo.domain.entities.SpotlightType
import javax.inject.Inject

class MarvelRepository @Inject constructor(private val marvelService: MarvelService) {

    /**
     * Buscar todos os personagens e os favoritos, através dos parametros
     */

    fun getMarvelCharacters() = Pager(
        config = PagingConfig(Constants.DEFAULT_PAGE_SIZE, enablePlaceholders = false)
    ) {
        MarvelCharactersDataSource(marvelService)
    }.liveData

    fun searchForMarvelCharacters(query: String) = Pager(
        config = PagingConfig(Constants.DEFAULT_PAGE_SIZE, enablePlaceholders = false)
    ) {
        MarvelCharactersDataSource(marvelService, query)
    }.flow

    fun getMarvelFavoriteCharacters() = Pager(
        config = PagingConfig(Constants.DEFAULT_PAGE_SIZE, enablePlaceholders = false)
    ) {
        MarvelCharactersDataSource(marvelService)
    }.liveData

    /**
     * Buscar as descrições do personagem
     */

    suspend fun getMarvelCharacter(characterId: Int) = marvelService.getCharacter(characterId)

    suspend fun getMarvelCharacterSpotlights(
        characterId: Int,
        spotlightType: SpotlightType,
    ) = when (spotlightType) {
        SpotlightType.COMICS -> marvelService.getComics(characterId).data.results
        SpotlightType.EVENTS -> marvelService.getEvents(characterId).data.results
        SpotlightType.SERIES -> marvelService.getSeries(characterId).data.results
        SpotlightType.STORIES -> marvelService.getStories(characterId).data.results
    }
}
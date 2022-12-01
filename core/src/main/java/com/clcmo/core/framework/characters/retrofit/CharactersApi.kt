package com.clcmo.core.framework.characters.retrofit

import com.clcmo.core.domain.entities.character.Character
import com.clcmo.core.framework.models.response.MarvelApiResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersApi {
    @GET("/v1/public/characters")
    fun getCharactersAsync(@Query("offset") offset: Int): Deferred<MarvelApiResponse<Character>>


    @GET("/v1/public/characters/{characterId}")
    fun getCharacterAsync(@Path("characterId") characterId: Int): Deferred<MarvelApiResponse<Character>>
}
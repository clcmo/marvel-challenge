package com.clcmo.core.data.repositories.characters

import com.clcmo.core.domain.entities.character.Character
import com.clcmo.core.framework.models.response.MarvelApiData

interface CharactersRemoteSource {
    suspend fun getCharacters(offset: Int = 0): MarvelApiData<Character>
    suspend fun getCharacter(id: Int): Character
}
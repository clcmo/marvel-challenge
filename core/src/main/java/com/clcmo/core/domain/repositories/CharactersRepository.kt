package com.clcmo.core.domain.repositories

import com.clcmo.core.domain.entities.PaginatedData
import com.clcmo.core.domain.entities.character.Character

interface CharactersRepository {
    suspend fun getCharacters(offset: Int = 0): PaginatedData<Character>
    suspend fun getCharacter(id: Int): Character
}
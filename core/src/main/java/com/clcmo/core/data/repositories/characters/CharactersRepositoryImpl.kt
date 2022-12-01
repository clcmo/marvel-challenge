package com.clcmo.core.data.repositories.characters

import com.clcmo.core.domain.entities.PaginatedData
import com.clcmo.core.domain.entities.character.Character
import com.clcmo.core.domain.repositories.CharactersRepository
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersRemoteSource: CharactersRemoteSource
) : CharactersRepository {

    override suspend fun getCharacters(offset: Int): PaginatedData<Character> {
        val getCharactersResult = charactersRemoteSource.getCharacters(offset)
        return PaginatedData(getCharactersResult.results, getCharactersResult.total)
    }

    override suspend fun getCharacter(id: Int) = charactersRemoteSource.getCharacter(id)
}
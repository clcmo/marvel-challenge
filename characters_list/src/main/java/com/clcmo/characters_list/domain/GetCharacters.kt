package com.clcmo.characters_list.domain

import com.clcmo.core.domain.repositories.CharactersRepository
import javax.inject.Inject

class GetCharacters @Inject constructor(private val charactersRepository: CharactersRepository) {
    suspend operator fun invoke(offset: Int = 0) = charactersRepository.getCharacters(offset)
}
package com.clcmo.character_details.domain

import com.clcmo.core.domain.repositories.CharactersRepository
import javax.inject.Inject

class GetCharacterDetails
@Inject constructor(private val charactersRepository: CharactersRepository) {
    suspend operator fun invoke(characterId: Int) = charactersRepository.getCharacter(characterId)
}


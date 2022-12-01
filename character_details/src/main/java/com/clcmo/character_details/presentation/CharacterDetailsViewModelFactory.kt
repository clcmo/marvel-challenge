package com.clcmo.character_details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.clcmo.character_details.domain.GetCharacterDetails

class CharacterDetailsViewModelFactory(
    private val characterId: Int,
    private val getCharacterDetails: GetCharacterDetails
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterDetailsViewModel::class.java)) {
            return CharacterDetailsViewModel(characterId, getCharacterDetails) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")

    }
}
package com.clcmo.character_details.di

import com.clcmo.character_details.domain.GetCharacterDetails
import com.clcmo.character_details.presentation.CharacterDetailsViewModelFactory
import com.clcmo.core.di.scopes.FeatureScope
import dagger.Module
import dagger.Provides

@Module
class CharacterDetailsModule(private val characterId: Int) {
    @Provides
    @FeatureScope
    fun providesCharacterDetailsViewModelFactory(getCharacterDetails: GetCharacterDetails) =
        CharacterDetailsViewModelFactory(characterId, getCharacterDetails)
}
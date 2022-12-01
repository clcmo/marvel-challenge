package com.clcmo.character_details.di

import com.clcmo.character_details.presentation.CharacterDetailsActivity
import com.clcmo.core.di.CoreComponent
import com.clcmo.core.di.scopes.FeatureScope
import dagger.Component

@Component(dependencies = [CoreComponent::class], modules = [CharacterDetailsModule::class])
@FeatureScope
interface CharacterDetailsComponent {
    fun inject(characterDetailsActivity: CharacterDetailsActivity)
}
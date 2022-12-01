package com.clcmo.characters_list.di

import com.clcmo.characters_list.presentation.CharactersActivity
import com.clcmo.core.di.CoreComponent
import com.clcmo.core.di.scopes.FeatureScope
import dagger.Component

@Component(dependencies = [CoreComponent::class])
@FeatureScope
interface CharactersComponent {
    fun inject(activity: CharactersActivity)
}
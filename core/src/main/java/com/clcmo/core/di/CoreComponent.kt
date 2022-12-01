package com.clcmo.core.di

import com.clcmo.core.domain.repositories.CharactersRepository
import dagger.Component
import javax.inject.Singleton

@Component(modules = [CoreModule::class])
@Singleton
interface CoreComponent {
    fun providesCharacterRepository(): CharactersRepository
}
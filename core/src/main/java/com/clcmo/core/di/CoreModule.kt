package com.clcmo.core.di

import com.clcmo.core.data.repositories.characters.CharactersRemoteSource
import com.clcmo.core.data.repositories.characters.CharactersRepositoryImpl
import com.clcmo.core.domain.repositories.CharactersRepository
import com.clcmo.core.framework.characters.CharactersRemoteSourceImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class CoreModule {
    @Binds
    @Singleton
    abstract fun providesCharactersRepository(charactersRepositoryImpl: CharactersRepositoryImpl): CharactersRepository

    @Binds
    @Singleton
    abstract fun providesCharactersRemoteSource(charactersRemoteSourceImpl: CharactersRemoteSourceImpl): CharactersRemoteSource
}
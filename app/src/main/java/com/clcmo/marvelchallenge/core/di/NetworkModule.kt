package com.clcmo.marvelchallenge.core.di

import com.clcmo.domain.entities.MarvelService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    @Singleton
    @Provides
    fun provideMarvelService(): MarvelService {
        return MarvelService.create()
    }
}
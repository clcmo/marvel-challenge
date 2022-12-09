package com.clcmo.marvelchallenge.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.clcmo.data.model.MarvelCharacter
import com.clcmo.domain.entities.SpotlightType
import com.clcmo.domain.repositories.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import com.clcmo.marvelchallenge.core.utils.Result.*

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val marvelRepository: MarvelRepository,
    private val state: SavedStateHandle
) :
    ViewModel() {

    val description = liveData(Dispatchers.IO) {
        emit(Loading)
        try {
            Success(
                marvelRepository.getMarvelCharacter(
                    state.get<MarvelCharacter>("marvelCharacter")?.id!!
                )
            )
        } catch (e: Exception){
            emit(Error(e))
        }
    }

    val comics = liveData(Dispatchers.IO) {
        emit(Loading)
        try {
            emit(
                Success(
                    marvelRepository.getMarvelCharacterSpotlights(
                        state.get<MarvelCharacter>("marvelCharacter")?.id!!,
                        SpotlightType.COMICS
                    )
                )
            )
        } catch (e: Exception) {
            emit(Error(e))
        }
    }

    val events = liveData(Dispatchers.IO) {
        emit(Loading)
        try {
            emit(
                Success(
                    marvelRepository.getMarvelCharacterSpotlights(
                        state.get<MarvelCharacter>("marvelCharacter")?.id!!,
                        SpotlightType.EVENTS
                    )
                )
            )
        } catch (e: Exception) {
            emit(Error(e))
        }
    }

    val series = liveData(Dispatchers.IO) {
        emit(Loading)
        try {
            emit(
                Success(
                    marvelRepository.getMarvelCharacterSpotlights(
                        state.get<MarvelCharacter>("marvelCharacter")?.id!!,
                        SpotlightType.SERIES
                    )
                )
            )
        } catch (e: Exception) {
            emit(Error(e))
        }
    }

    val stories = liveData(Dispatchers.IO) {
        emit(Loading)
        try {
            emit(
                Success(
                    marvelRepository.getMarvelCharacterSpotlights(
                        state.get<MarvelCharacter>("marvelCharacter")?.id!!,
                        SpotlightType.STORIES
                    )
                )
            )
        } catch (e: Exception) {
            emit(Error(e))
        }
    }
}
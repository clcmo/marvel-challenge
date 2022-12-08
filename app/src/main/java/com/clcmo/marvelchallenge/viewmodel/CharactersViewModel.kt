package com.clcmo.marvelchallenge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.clcmo.domain.repositories.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(marvelRepository: MarvelRepository) : ViewModel() {
    val marvelCharacters = marvelRepository.getMarvelCharacters().cachedIn(viewModelScope)
}
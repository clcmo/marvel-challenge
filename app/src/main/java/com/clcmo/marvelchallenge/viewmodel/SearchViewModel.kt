package com.clcmo.marvelchallenge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.clcmo.domain.repositories.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(marvelRepository: MarvelRepository) : ViewModel() {

    private val TAG = "SearchViewModel"

    private val searchQuery = MutableStateFlow("")

    @ExperimentalCoroutinesApi
    @FlowPreview
    val marvelCharacters = searchQuery
        .debounce(500)
        .filter {
            return@filter it.isNotEmpty()
        }
        .flatMapLatest {
            marvelRepository.searchForMarvelCharacters(it)
        }
        .asLiveData()
        .cachedIn(viewModelScope)

    fun searchForCharacters(query: String?) {
        query?.let { q ->
            searchQuery.value = q
        }
    }
}
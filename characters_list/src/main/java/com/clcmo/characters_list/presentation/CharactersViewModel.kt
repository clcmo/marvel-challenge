package com.clcmo.characters_list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.clcmo.characters_list.domain.GetCharacters
import com.clcmo.core.presentation.UIState
import com.clcmo.core.presentation.helpers.livedata.Event
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    getCharacters: GetCharacters
) : ViewModel() {
    private val charactersDataSourceFactory = CharactersDataSourceFactory(getCharacters, viewModelScope)

    private val config = PagedList.Config.Builder()
        .setPageSize(20)
        .setEnablePlaceholders(true)
        .build()

    val characters: LiveData<PagedList<Char>> =
        LivePagedListBuilder(charactersDataSourceFactory, config).build()
    val charactersUiState: LiveData<Event<UIState>> =
        Transformations.switchMap(charactersDataSourceFactory.charactersDataSource) { it.charactersUiState }

    fun refresh() = charactersDataSourceFactory.charactersDataSource.value?.invalidate()
}
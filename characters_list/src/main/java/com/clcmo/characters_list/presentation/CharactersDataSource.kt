package com.clcmo.characters_list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PositionalDataSource
import com.clcmo.characters_list.domain.GetCharacters
import com.clcmo.core.presentation.UIState
import com.clcmo.core.presentation.helpers.livedata.Event
import com.clcmo.core.presentation.helpers.livedata.toEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersDataSource @Inject constructor(
    private val getCharacters: GetCharacters,
    private val viewModelScope: CoroutineScope
) : PositionalDataSource<Char>() {

    private val _charactersState = MutableLiveData<Event<UIState>>()
    val charactersUiState: LiveData<Event<UIState>> = _charactersState

    override fun invalidate() {
        _charactersState.postValue(UIState.Loading.toEvent())
        super.invalidate()
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Char>) {
        viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
            _charactersState.postValue(exception.localizedMessage?.let { UIState.Error(it).toEvent() })
        }) {
            _charactersState.postValue(UIState.Loading.toEvent())
            val getCharactersResult = getCharacters()

            callback.onResult(getCharactersResult.data, 0, getCharactersResult.totalData)
            _charactersState.postValue(UIState.Complete.toEvent())
        }
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Char>) {
        viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
            _charactersState.postValue(exception.localizedMessage?.let { UIState.Error(it).toEvent() })
        }) {
            val marvelApiCharactersResponse = getCharacters(params.startPosition)

            callback.onResult(marvelApiCharactersResponse.data)
            _charactersState.postValue(UIState.Complete.toEvent())
        }
    }
}

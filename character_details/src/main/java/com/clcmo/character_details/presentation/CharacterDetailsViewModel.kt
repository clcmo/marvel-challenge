package com.clcmo.character_details.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clcmo.character_details.domain.GetCharacterDetails
import com.clcmo.core.presentation.UIState
import com.clcmo.core.presentation.helpers.livedata.Event
import com.clcmo.core.presentation.helpers.livedata.toEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(
    private val characterId: Int,
    private val getCharacterDetails: GetCharacterDetails
) : ViewModel() {

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description

    private val _thumbnailUrl = MutableLiveData<String>()
    val thumbnailUrl: LiveData<String> = _thumbnailUrl

    private val _getCharacterDetailsState = MutableLiveData<Event<UIState>>()
    val getCharacterDetailsUiState: LiveData<Event<UIState>> = _getCharacterDetailsState

    init {
        getCharacterDetails()
    }

    fun getCharacterDetails() {
        viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
            _getCharacterDetailsState.postValue(exception.localizedMessage?.let { UIState.Error(it).toEvent() })
        }) {
            _getCharacterDetailsState.postValue(UIState.Loading.toEvent())
            val character = getCharacterDetails(characterId)

            _name.postValue(character.name)
            _description.postValue(character.description)
            _thumbnailUrl.postValue(character.thumbnail.getUrl())

            _getCharacterDetailsState.postValue(UIState.Complete.toEvent())
        }
    }
}
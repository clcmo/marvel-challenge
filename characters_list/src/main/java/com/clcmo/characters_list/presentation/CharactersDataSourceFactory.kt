package com.clcmo.characters_list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.clcmo.characters_list.domain.GetCharacters
import com.clcmo.core.domain.entities.character.Character
import kotlinx.coroutines.CoroutineScope

class CharactersDataSourceFactory(
    private val getCharacters: GetCharacters,
    private val viewModelScope: CoroutineScope
) : DataSource.Factory<Int, Char>() {

    private val _charactersDataSource = MutableLiveData<CharactersDataSource>()
    val charactersDataSource: LiveData<CharactersDataSource> = _charactersDataSource

    override fun create(): DataSource<Int, Character> {
        val newCharactersDataSource = CharactersDataSource(getCharacters, viewModelScope)
        _charactersDataSource.postValue(newCharactersDataSource)
        return newCharactersDataSource
    }
}
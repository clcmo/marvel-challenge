package com.clcmo.characters_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.clcmo.characters_list.domain.GetCharacters
import javax.inject.Inject

class CharactersViewModelFactory @Inject constructor(
    private val getCharacters: GetCharacters
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CharactersViewModel::class.java)) {
            return CharactersViewModel(getCharacters) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
package com.clcmo.core.framework.characters

import com.clcmo.core.data.repositories.characters.CharactersRemoteSource
import com.clcmo.core.framework.BaseRemoteSource
import com.clcmo.core.framework.characters.retrofit.CharactersApi
import javax.inject.Inject

class CharactersRemoteSourceImpl @Inject constructor() : BaseRemoteSource<CharactersApi>(
    CharactersApi::class.java
), CharactersRemoteSource {

    override suspend fun getCharacters(offset: Int) = api.getCharactersAsync(offset).await().data
    override suspend fun getCharacter(id: Int) =
        api.getCharacterAsync(id).await().data.results.first()
}
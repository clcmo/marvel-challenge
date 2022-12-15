package com.clcmo.domain.repositories

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.clcmo.data.model.MarvelCharacter
import com.clcmo.data.Constants
import com.clcmo.domain.entities.MarvelService
import java.io.IOException
import kotlin.math.ceil

class MarvelCharactersDataSource(
    private val marvelService: MarvelService,
    private val query: String? = null,
) :
    PagingSource<Int, MarvelCharacter>() {

    private val TAG = "CharactersDataSource"

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MarvelCharacter> {
        val pageNumber = params.key ?: 0

        return try {
            val response = marvelService.getCharacters(
                limit = Constants.DEFAULT_PAGE_SIZE,
                offset = pageNumber * Constants.CHARACTERS_OFFSET,
                nameStartsWith = query
            )

            val data = response.data

            val prevKey = when {
                pageNumber > 0 -> pageNumber - 1
                else -> null
            }

            val totalPages = ceil(data.total.toFloat() / data.total.toFloat()).toInt()

            val nextKey = when {
                pageNumber < totalPages -> pageNumber + 1
                else -> null
            }

            LoadResult.Page(
                data = data.results,
                prevKey = prevKey,
                nextKey = nextKey
            )

        } catch (e: IOException) {
            Log.e(TAG, "load: $e")
            LoadResult.Error(e)
        } catch (e: HttpException) {
            Log.e(TAG, "load: $e")
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MarvelCharacter>): Int? =
        state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
}


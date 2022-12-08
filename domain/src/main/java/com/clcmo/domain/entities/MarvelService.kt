package com.clcmo.domain.entities

import android.util.Log
import com.clcmo.data.model.CharacterSpotlight
import com.clcmo.data.model.MarvelCharacter
import com.clcmo.domain.MarvelDomainConstants
import com.clcmo.domain.entities.dto.ResponseDTO
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.math.BigInteger
import java.security.MessageDigest

interface MarvelService {

    @GET("v1/public/characters")
    suspend fun getCharacters(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int = 0,
        @Query("nameStartsWith") nameStartsWith: String? = null
    ): ResponseDTO<MarvelCharacter>

    @GET("v1/public/characters/{characterId}/comics")
    suspend fun getComics(@Path("characterId") characterId: Int): ResponseDTO<CharacterSpotlight>

    @GET("v1/public/characters/{characterId}/events")
    suspend fun getEvents(@Path("characterId") characterId: Int): ResponseDTO<CharacterSpotlight>

    @GET("v1/public/characters/{characterId}/series")
    suspend fun getSeries(@Path("characterId") characterId: Int): ResponseDTO<CharacterSpotlight>

    @GET("v1/public/characters/{characterId}/stories")
    suspend fun getStories(@Path("characterId") characterId: Int): ResponseDTO<CharacterSpotlight>

    companion object {
        fun create(): MarvelService {
            val moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()

            val okHttpClient = createOkHttpClient()

            return Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(MarvelDomainConstants.BASE_URL)
                .build()
                .create(MarvelService::class.java)
        }


        private fun createOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val currentTime = System.currentTimeMillis()

                    val request = chain.request().newBuilder()
                    val originalUrl = chain.request().url()
                    val newUrl = originalUrl.newBuilder()
                        .addQueryParameter(MarvelDomainConstants.TIMESTAMP_QUERY_KEY, currentTime.toString())
                        .addQueryParameter(MarvelDomainConstants.API_KEY_QUERY_KEY, MarvelDomainConstants.MARVEL_API_KEY)
                        .addQueryParameter(
                            MarvelDomainConstants.HASH_QUERY_KEY,
                            createHash("$currentTime$MarvelDomainConstants.MARVEL_PRIVATE_API_KEY$MarvelDomainConstants.MARVEL_API_KEY")
                        )
                        .build()

                    request.url(newUrl)
                    val response = chain.proceed(request.build())
                    Log.d(MarvelDomainConstants.TAG, "Code : $response.code()")
                    if(response.code() == 401){
                        return@addInterceptor response
                    }
                    return@addInterceptor response
                }
                .build()
        }

        private fun createHash(input: String): String {
            val md5 = MessageDigest.getInstance("MD5")
            return BigInteger(1, md5.digest(input.toByteArray()))
                .toString(16).padStart(32, '0')
        }
    }
}
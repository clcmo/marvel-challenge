package com.clcmo.domain

import com.clcmo.domain.BuildConfig.MARVEL_BASE_URL
import com.clcmo.domain.BuildConfig.MARVEL_API_KEY
import com.clcmo.domain.BuildConfig.MARVEL_PRIVATE_API_KEY

object MarvelDomainConstants {
    const val TAG = "Marvel App"
    //Calling API
    const val BASE_URL = MARVEL_BASE_URL
    const val API_KEY = MARVEL_API_KEY
    const val PRIVATE_API_KEY = MARVEL_PRIVATE_API_KEY

    const val TIMESTAMP_QUERY_KEY = "ts"
    const val API_KEY_QUERY_KEY = "apikey"
    const val HASH_QUERY_KEY = "hash"
}
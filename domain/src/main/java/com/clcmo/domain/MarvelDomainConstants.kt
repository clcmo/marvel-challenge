package com.clcmo.domain

import com.clcmo.domain.BuildConfig.*

object MarvelDomainConstants {
    const val TAG = "Marvel App"
    //Calling API
    const val BASE_URL = MARVEL_API_BASE_URL
    const val MARVEL_API_KEY = MARVEL_API_PUBLIC_KEY
    const val MARVEL_PRIVATE_API_KEY = MARVEL_API_HASH

    const val TIMESTAMP_QUERY_KEY = "ts"
    const val API_KEY_QUERY_KEY = "apikey"
    const val HASH_QUERY_KEY = "hash"
}
package com.clcmo.core.presentation.app

import android.app.Activity
import android.app.Application
import android.content.Context
import com.clcmo.core.di.CoreComponent
import com.clcmo.core.di.DaggerCoreComponent

class MarvelApplication : Application() {

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.create()
    }

    companion object {
        @JvmStatic
        fun coreComponent(context: Context) =
            (context.applicationContext as MarvelApplication).coreComponent
    }
}

fun Activity.coreComponent() = MarvelApplication.coreComponent(this)
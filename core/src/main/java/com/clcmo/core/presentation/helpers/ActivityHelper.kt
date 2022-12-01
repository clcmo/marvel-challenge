@file:JvmName("ActivityHelper")
package com.clcmo.core.presentation.helpers

import android.content.Context
import android.content.Intent

const val PACKAGE_NAME = "com.clcmo.marvelchallenge"

fun Context.intentTo(activity: AddressableActivity) =
    Intent(this, Class.forName(activity.className))

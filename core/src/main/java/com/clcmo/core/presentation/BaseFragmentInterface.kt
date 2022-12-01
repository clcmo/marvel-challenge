package com.clcmo.core.presentation

import android.view.View

interface BaseFragmentInterface {
    fun setupPage(view: View)
    fun subscribeToInputs(view: View)
    fun subscribeToOutputs(view: View)
}
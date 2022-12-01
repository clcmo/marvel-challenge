package com.clcmo.core.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.clcmo.core.R

class EmptyView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {
    init {
        LayoutInflater.from(this.context).inflate(R.layout.view_empty, this, true)
    }
}
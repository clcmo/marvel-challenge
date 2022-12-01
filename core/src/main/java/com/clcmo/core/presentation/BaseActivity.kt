package com.clcmo.core.presentation

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<T:BaseViewModel> : AppCompatActivity() {
    abstract val viewModel: T
    abstract val layout: Int

    open fun setupPage() {}
    open fun setupInputs() {}
    open fun setupOutputs() {}

    abstract fun inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        setContentView(layout)
        setupPage()
        setupInputs()
    }

    override fun onResume() {
        super.onResume()
        setupOutputs()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}


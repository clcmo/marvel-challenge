package com.clcmo.characters_list.presentation

import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.clcmo.characters_list.R
import com.clcmo.characters_list.di.DaggerCharactersComponent
import com.clcmo.core.presentation.BaseActivity
import com.clcmo.core.presentation.UIState
import com.clcmo.core.presentation.app.coreComponent
import com.clcmo.core.presentation.helpers.livedata.EventObserver
import com.clcmo.core.presentation.views.EmptyView
import com.clcmo.core.presentation.views.LoadingView
import javax.inject.Inject

class CharactersActivity : BaseActivity<CharactersViewModel>() {

    override val layout: Int = R.layout.activity_characters_list

    @Inject
    lateinit var factory: CharactersViewModelFactory
    override val viewModel: CharactersViewModel by lazy {
        ViewModelProviders.of(this, factory).get(CharactersViewModel::class.java)
    }

    private val charactersAdapter by lazy { CharactersPagedListAdapter() }

    private val swpCharacters by lazy { findViewById<SwipeRefreshLayout>(R.id.swp_characters) }
    private val recCharacters by lazy { findViewById<RecyclerView>(R.id.rec_characters) }
    private val loadingCharacters by lazy { findViewById<LoadingView>(R.id.loading_characters) }
    private val emptyCharacters by lazy { findViewById<EmptyView>(R.id.empty_characters) }
    private val noInternet by lazy { findViewById<EmptyView>(R.id.no_internet) }

    override fun inject() {
        DaggerCharactersComponent
            .builder()
            .coreComponent(coreComponent())
            .build()
            .inject(this)
    }

    override fun setupPage() {
        super.setupPage()
        recCharacters.adapter = charactersAdapter
    }

    override fun setupInputs() {
        super.setupInputs()
        swpCharacters.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    override fun setupOutputs() {
        super.setupOutputs()
        viewModel.characters.observe(this) {
            charactersAdapter.submitList(it)
        }

        viewModel.charactersUiState.observe(this, EventObserver {
            recCharacters.visibility =
                when {
                    it is UIState.Complete || swpCharacters.isRefreshing -> View.VISIBLE
                    else -> View.INVISIBLE
                }
            loadingCharacters.visibility =
                when {
                    it is UIState.Loading && !swpCharacters.isRefreshing -> View.VISIBLE
                    else -> View.GONE
                }
            emptyCharacters.visibility =
                when (it) {
                    is UIState.Error -> View.VISIBLE
                    else -> View.GONE
                }
            noInternet.visibility =
                when (it) {
                    is UIState.NoInternet -> View.VISIBLE
                    else -> View.GONE
                }

            swpCharacters.isRefreshing = false
        })
    }
}
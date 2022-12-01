package com.clcmo.character_details.presentation

import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.Group
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.clcmo.character_details.R
import com.clcmo.character_details.di.CharacterDetailsModule
import com.clcmo.character_details.di.DaggerCharacterDetailsComponent
import com.clcmo.core.presentation.BaseActivity
import com.clcmo.core.presentation.UIState
import com.clcmo.core.presentation.app.GlideApp
import com.clcmo.core.presentation.app.coreComponent
import com.clcmo.core.presentation.helpers.CharacterDetailsObject
import com.clcmo.core.presentation.helpers.livedata.EventObserver
import com.clcmo.core.presentation.views.LoadingView
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class CharacterDetailsActivity : BaseActivity<CharacterDetailsViewModel>() {

    override val layout: Int = R.layout.activity_character_details

    @Inject
    lateinit var factory: CharacterDetailsViewModelFactory
    override val viewModel: CharacterDetailsViewModel by lazy {
        ViewModelProviders.of(this, factory).get(CharacterDetailsViewModel::class.java)
    }

    private val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar_cd) }
    private val coordinatorLayout by lazy { findViewById<CoordinatorLayout>(R.id.coord_layout_cd) }
    private val swipeRefreshLayout by lazy { findViewById<SwipeRefreshLayout>(R.id.swp_ref_layout_cd) }
    private val imgThumbnail by lazy { findViewById<AppCompatImageView>(R.id.img_thumbnail) }
    private val txtName by lazy { findViewById<AppCompatTextView>(R.id.txt_name) }
    private val txtDescription by lazy { findViewById<AppCompatTextView>(R.id.txt_description) }
    private val grpDetails by lazy { findViewById<Group>(R.id.grp_details) }
    private val loadingCharacter by lazy { findViewById<LoadingView>(R.id.loading_character) }

    override fun inject() {
        val characterId = intent.getIntExtra(CharacterDetailsObject.EXTRA_CHARACTER_ID, 0)

        DaggerCharacterDetailsComponent
            .builder()
            .coreComponent(coreComponent())
            .characterDetailsModule(CharacterDetailsModule(characterId))
            .build()
            .inject(this)
    }


    override fun setupPage() {
        super.setupPage()
        setSupportActionBar(toolbar)
        title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun setupInputs() {
        super.setupInputs()
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.getCharacterDetails()
        }
    }

    override fun setupOutputs() {
        super.setupOutputs()
        viewModel.name.observe(this) { txtName.text = it }

        viewModel.description.observe(this) {
            txtDescription.text = it.ifEmpty {
                getString(R.string.no_description_label)
            }
        }

        viewModel.thumbnailUrl.observe(this) {
            GlideApp.with(imgThumbnail)
                .load(it)
                .placeholder(ContextCompat.getDrawable(this, R.color.blue))
                .centerInside()
                .into(imgThumbnail)
        }

        viewModel.getCharacterDetailsUiState.observe(this, EventObserver {
            when (it) {
                is UIState.Error -> {
                    Toast.makeText(this, it.errorMessage, Toast.LENGTH_SHORT).show()
                    Snackbar.make(coordinatorLayout, it.errorMessage, Snackbar.LENGTH_SHORT).show()
                }
                else -> {
                    grpDetails.visibility = when (it) {
                        is UIState.Complete -> View.VISIBLE
                        else -> View.GONE
                    }
                    loadingCharacter.visibility =
                        when (it) {
                            is UIState.Loading -> View.VISIBLE
                            else -> View.GONE
                        }
                }
            }
            swipeRefreshLayout.isRefreshing = false
        })
    }
}
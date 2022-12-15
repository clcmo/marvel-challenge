package com.clcmo.marvelchallenge.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.clcmo.data.model.CharacterSpotlight
import com.clcmo.marvelchallenge.R
import com.clcmo.marvelchallenge.adapters.SpotlightsAdapter
import com.clcmo.marvelchallenge.core.utils.Result
import com.clcmo.marvelchallenge.databinding.FragmentItemCharacterDetailsBinding
import com.clcmo.marvelchallenge.databinding.ViewResultStateBinding
import com.clcmo.marvelchallenge.viewmodel.DetailsViewModel
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterItemDetailsFragment : Fragment(), AppBarLayout.OnOffsetChangedListener {

    private lateinit var binding: FragmentItemCharacterDetailsBinding
    private val args: CharacterItemDetailsFragmentArgs by navArgs()
    private val viewModel: DetailsViewModel by viewModels()

    private var scrollRange = -1
    private var isShown = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemCharacterDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.character = args.marvelCharacter
        binding.appBarLayout.addOnOffsetChangedListener(this)
        binding.toolbar.setNavigationOnClickListener { findNavController().navigateUp() }

        binding.isFavorite.setOnClickListener {
            setFavorite()
        }

        setupSpotlightRecyclerViews(
            binding.comicsRecyclerView,
            binding.eventsRecyclerView,
            binding.seriesRecyclerView,
            binding.storiesRecyclerView
        )

        observeSpotlightStates(
            Pair(viewModel.comics, binding.comicsResultState),
            Pair(viewModel.events, binding.eventsResultState),
            Pair(viewModel.series, binding.seriesResultState),
            Pair(viewModel.stories, binding.storiesResultState)
        )
    }

    private fun setFavorite(): Int = when (binding.character?.isFavorite) {
        true -> R.drawable.ic_heart
        else -> R.drawable.ic_heart_border
    }

    override fun onDestroyView() {
        binding.appBarLayout.removeOnOffsetChangedListener(this)
        super.onDestroyView()
    }

    private fun setupSpotlightRecyclerViews(vararg recyclerViews: RecyclerView) {
        recyclerViews.forEach {
            with(it) {
                adapter = SpotlightsAdapter()
                layoutManager =
                    LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
            }
        }
    }

    private fun observeSpotlightStates(
        vararg spotlights: Pair<LiveData<Result<List<CharacterSpotlight>>>, ViewResultStateBinding>
    ) {
        spotlights.forEach { entry ->
            entry.first.observe(viewLifecycleOwner) {
                val resultStateBinding = entry.second

                resultStateBinding.loadingState.visibility = when (it) {
                    is Result.Loading -> View.VISIBLE
                    else -> View.INVISIBLE
                }
                resultStateBinding.successEmptyState.visibility = when {
                    it is Result.Success && it.dataIfAvailable?.isEmpty() == true -> View.VISIBLE
                    else -> View.INVISIBLE
                }
                resultStateBinding.errorState.visibility = when (it) {
                    is Result.Error -> View.VISIBLE
                    else -> View.INVISIBLE
                }
            }
        }
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        if (scrollRange == -1) {
            scrollRange = appBarLayout.totalScrollRange
        }

        when {
            scrollRange + verticalOffset == 0 -> {
                binding.toolbar.title = args.marvelCharacter.name
                binding.toolbar.setNavigationIcon(R.drawable.ic_back)
                isShown = true
            }
            isShown -> {
                binding.toolbar.title = ""
                binding.toolbar.setNavigationIcon(R.drawable.ic_back_bg)
                isShown = false
            }
        }
    }
}


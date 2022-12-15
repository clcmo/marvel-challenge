package com.clcmo.marvelchallenge.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.clcmo.marvelchallenge.R
import com.clcmo.marvelchallenge.adapters.CharactersListAdapter
import com.clcmo.marvelchallenge.adapters.LoadingStateAdapter
import com.clcmo.marvelchallenge.databinding.FragmentCharactersSearchBinding
import com.clcmo.marvelchallenge.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@AndroidEntryPoint
class CharactersSearchFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentCharactersSearchBinding
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.cancelSearch.setOnClickListener { findNavController().navigateUp() }
        binding.characterSearchView.setOnQueryTextListener(this)
        setupSearchCharactersRecyclerView()
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    private fun setupSearchCharactersRecyclerView() {
        val charactersAdapter = CharactersListAdapter(R.layout.item_view_search_character) {
            findNavController().navigate(
                CharactersSearchFragmentDirections.actionSearchFragmentToDetailsFragment(it)
            )
        }

        with(binding.charactersRecyclerView) {
            adapter = charactersAdapter.withLoadStateHeaderAndFooter(
                LoadingStateAdapter { charactersAdapter.retry() },
                LoadingStateAdapter { charactersAdapter.retry() }
            )
        }

        viewModel.marvelCharacters.observe(viewLifecycleOwner) {
            charactersAdapter.submitData(lifecycle, it)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean = false

    override fun onQueryTextChange(newText: String?): Boolean {
        viewModel.searchForCharacters(newText)
        return true
    }
}
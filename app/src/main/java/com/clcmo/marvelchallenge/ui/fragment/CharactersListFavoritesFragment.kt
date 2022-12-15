package com.clcmo.marvelchallenge.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.clcmo.marvelchallenge.R
import com.clcmo.marvelchallenge.adapters.CharactersListAdapter
import com.clcmo.marvelchallenge.adapters.LoadingStateAdapter
import com.clcmo.marvelchallenge.databinding.FragmentCharactersFavoritesBinding
import com.clcmo.marvelchallenge.viewmodel.FavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersListFavoritesFragment : Fragment(), Toolbar.OnMenuItemClickListener {

    private lateinit var binding: FragmentCharactersFavoritesBinding
    private val viewModel: FavoritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        binding.lifecycleOwner = this
        binding.toolbar.setOnMenuItemClickListener(this)
        setupCharactersRecyclerView()
    }

    private fun setupCharactersRecyclerView() {
        val charactersAdapter = CharactersListAdapter(R.layout.item_view_character) {
            findNavController().navigate(
                CharactersListFragmentDirections.actionListFavoritesFragmentToDetailsFragment(it)
            )
        }

        with(binding.favoritesRecyclerView) {
            adapter = charactersAdapter.withLoadStateHeaderAndFooter(
                LoadingStateAdapter { charactersAdapter.retry() },
                LoadingStateAdapter { charactersAdapter.retry() }
            )
        }

        viewModel.marvelCharacters.observe(viewLifecycleOwner) {
            charactersAdapter.submitData(lifecycle, it)
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_search) {
            findNavController().navigate(
                CharactersListFragmentDirections.actionListFavoritesFragmentToSearchFragment()
            )
        }
        return false
    }
}


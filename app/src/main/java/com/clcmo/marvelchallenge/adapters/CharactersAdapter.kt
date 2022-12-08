package com.clcmo.marvelchallenge.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.clcmo.data.model.MarvelCharacter
import com.clcmo.marvelchallenge.databinding.ItemViewCharacterBinding
import com.clcmo.marvelchallenge.databinding.ItemViewSearchCharacterBinding

class CharactersAdapter(
    private val itemLayoutId: Int,
    private val block: (marvelCharacter: MarvelCharacter) -> Unit,
) :
    PagingDataAdapter<MarvelCharacter, CharactersAdapter.CharactersViewHolder>(
        characterDiffUtil
    ) {

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item)
            holder.itemView.setOnClickListener { block(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder =
        CharactersViewHolder.from(itemLayoutId, parent)

    class CharactersViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MarvelCharacter) {
            when (binding) {
                is ItemViewCharacterBinding -> binding.character = item
                is ItemViewSearchCharacterBinding -> binding.character = item
            }

            binding.executePendingBindings()
        }

        companion object {
            fun from(itemLayoutId: Int, parent: ViewGroup): CharactersViewHolder {
                val binding: ViewDataBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    itemLayoutId,
                    parent,
                    false
                )
                return CharactersViewHolder(binding)
            }
        }
    }

    companion object {
        private val characterDiffUtil = object : DiffUtil.ItemCallback<MarvelCharacter>() {
            override fun areItemsTheSame(
                oldItem: MarvelCharacter,
                newItem: MarvelCharacter,
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: MarvelCharacter,
                newItem: MarvelCharacter,
            ) = oldItem == newItem
        }
    }
}


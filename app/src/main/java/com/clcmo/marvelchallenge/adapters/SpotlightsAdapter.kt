package com.clcmo.marvelchallenge.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.clcmo.data.model.CharacterSpotlight
import com.clcmo.marvelchallenge.databinding.ItemViewSpotlightCharacterBinding


class SpotlightsAdapter : ListAdapter<CharacterSpotlight, SpotlightsAdapter.SpotlightsViewHolder>(
    spotlightDiffUtil
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpotlightsViewHolder =
        SpotlightsViewHolder.from(parent)

    override fun onBindViewHolder(holder: SpotlightsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class SpotlightsViewHolder(private val binding: ItemViewSpotlightCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CharacterSpotlight) {
            binding.spotlight = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): SpotlightsViewHolder {
                val binding = ItemViewSpotlightCharacterBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return SpotlightsViewHolder(binding)
            }
        }
    }

    companion object {
        private val spotlightDiffUtil = object : DiffUtil.ItemCallback<CharacterSpotlight>() {
            override fun areItemsTheSame(
                oldItem: CharacterSpotlight,
                newItem: CharacterSpotlight,
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: CharacterSpotlight,
                newItem: CharacterSpotlight,
            ) = oldItem == newItem
        }
    }
}
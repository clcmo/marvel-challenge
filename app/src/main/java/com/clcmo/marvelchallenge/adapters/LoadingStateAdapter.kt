package com.clcmo.marvelchallenge.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.clcmo.marvelchallenge.databinding.ViewLoadingStateBinding


class LoadingStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<LoadingStateAdapter.PagingLoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): PagingLoadStateViewHolder = PagingLoadStateViewHolder.from(parent)

    override fun onBindViewHolder(holder: PagingLoadStateViewHolder, loadState: LoadState) =
        holder.bind(loadState, retry)

    class PagingLoadStateViewHolder(
        private val binding: ViewLoadingStateBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState, retry: () -> Unit) {
            binding.loadingProgress.visibility = getLoadingState(loadState)
            binding.errorText.visibility = getErrorState(loadState)
            binding.retryButton.visibility = getErrorState(loadState)
            binding.retryButton.setOnClickListener { retry.invoke() }
            binding.executePendingBindings()
        }

        private fun getLoadingState(loadState: LoadState) : Int = when (loadState is LoadState.Loading) {
            true -> 1
            else -> 0
        }

        private fun getErrorState(loadState: LoadState) : Int = when (loadState is LoadState.Error){
            true -> 1
            else -> 0
        }

        companion object {
            fun from(parent: ViewGroup): PagingLoadStateViewHolder {
                val binding = ViewLoadingStateBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                return PagingLoadStateViewHolder(binding)
            }
        }
    }
}


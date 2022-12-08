package com.clcmo.marvelchallenge.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.clcmo.data.model.CharacterSpotlight
import com.clcmo.marvelchallenge.R

@BindingAdapter("setImage")
fun ImageView.bindCharacterImage(imageUrl: String?) {
    Glide.with(this.context)
        .load(imageUrl)
        .centerCrop()
        .into(this)
}

@BindingAdapter("setSpotlightImage")
fun ImageView.bindSpotlightImage(imageUrl: String?) {
    Glide.with(this.context)
        .load(imageUrl)
        .placeholder(R.drawable.image_placeholder)
        .error(R.drawable.image_placeholder)
        .centerCrop()
        .into(this)
}

@BindingAdapter("submitData")
fun RecyclerView.bindData(spotlights: List<CharacterSpotlight>?) {
    spotlights?.let {
        (adapter as SpotlightsAdapter).submitList(spotlights)
    }
}
package com.clcmo.characters_list.presentation

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.clcmo.characters_list.R
import com.clcmo.core.presentation.BasePagedListAdapter
import com.clcmo.core.presentation.app.GlideApp
import com.clcmo.core.presentation.helpers.Activities
import com.clcmo.core.presentation.helpers.intentTo
import com.clcmo.core.presentation.views.LoadingView

class CharactersPagedListAdapter : BasePagedListAdapter<Char>(
    itemsSame = { old, new -> old.id == new.id },
    contentsSame = { old, new -> old == new }
) {
    override val itemLayout: Int = R.layout.list_item_character

    override fun bindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val character = getItem(position)

        holder.itemView.apply {
            val imgThumbnail = findViewById<ImageView>(R.id.img_thumbnail)
            val txtName = findViewById<TextView>(R.id.txt_name)
            val loadingCharacter = findViewById<LoadingView>(R.id.loading_character)

            txtName.visibility = if (character != null) View.VISIBLE else View.INVISIBLE
            loadingCharacter.visibility = if (character == null) View.VISIBLE else View.INVISIBLE

            when {
                character != null -> {
                    txtName.text = character.name

                    GlideApp.with(context)
                        .load(character.thumbnail.getUrl())
                        .placeholder(ContextCompat.getDrawable(context, R.color.blue))
                        .centerInside()
                        .into(imgThumbnail)

                    setOnClickListener {
                        val intent = context.intentTo(Activities.CharacterDetails)
                            .putExtra(Activities.CharacterDetails.EXTRA_CHARACTER_ID, character.id)

                        context.startActivity(intent)
                    }
                }
                else -> {
                    GlideApp.with(context).clear(imgThumbnail)
                }
            }
        }
    }
}
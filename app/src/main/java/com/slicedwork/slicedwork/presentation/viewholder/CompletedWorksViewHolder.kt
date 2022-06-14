package com.slicedwork.slicedwork.presentation.viewholder

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.slicedwork.slicedwork.databinding.ItemCompletedWorksBinding
import com.slicedwork.slicedwork.util.enumerator.OccupationAreaEnum.*

class CompletedWorksViewHolder(
    private val binding: ItemCompletedWorksBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(occupationArea: Int, context: Context) {
        when (occupationArea) {
            PAINTING.ordinal -> PAINTING.run {
                hideUpLine()
                setProps(getText(context), getImage(context))
            }
            CLEANING.ordinal -> CLEANING.run {setProps(getText(context), getImage(context))}
            GARDENING.ordinal -> GARDENING.run {setProps(getText(context), getImage(context))}
            CONSTRUCTION.ordinal -> CONSTRUCTION.run {setProps(getText(context), getImage(context))}
            ELECTRIC.ordinal -> ELECTRIC.run {setProps(getText(context), getImage(context))}
            else -> PLUMBING.run {setProps(getText(context), getImage(context))}
        }
    }

    private fun hideUpLine() {
        binding.viewHorizontal.visibility = View.GONE
    }

    private fun setProps(text: String, image: Drawable?) {
        binding.run {
            ivOccupationArea.setImageDrawable(image)
            tvOccupationArea.text = text
        }
    }
}
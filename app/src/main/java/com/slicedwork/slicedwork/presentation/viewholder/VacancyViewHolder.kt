package com.slicedwork.slicedwork.presentation.viewholder

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.slicedwork.slicedwork.databinding.ItemVacancyBinding
import com.slicedwork.slicedwork.domain.model.Vacancy
import com.slicedwork.slicedwork.util.enumerator.OccupationAreaEnum.*

class VacancyViewHolder(
    private val binding: ItemVacancyBinding,
    private val onItemClickListener: (vacancy: Vacancy) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var occupationAreaImage: Drawable
    private lateinit var occupationAreaText: String

    fun bind(vacancy: Vacancy, context: Context) {
        vacancy.run {
            val cityState = "$city - $state"
            val signPrice = "R$ $price"
            val idWithHashtag = "#$id"

            binding.run {

                setOccupationAreaProps(occupationArea.toInt(), context)
                tvId.text = idWithHashtag
                tvOccupationArea.text = occupationAreaText
                tvTask.text = task
                tvCityState.text = cityState
                tvPrice.text = signPrice

                root.setOnClickListener {
                    onItemClickListener.invoke(vacancy)
                }
            }
        }
    }

    private fun setOccupationAreaProps(occupationArea: Int, context: Context) {
        when (occupationArea) {
            PAINTING.ordinal -> {
                occupationAreaImage = context.getDrawable(PAINTING.getImage())!!
                occupationAreaText = PAINTING.getText(occupationArea, context)
            }
            CLEANING.ordinal -> {
                occupationAreaImage = context.getDrawable(CLEANING.getImage())!!
                occupationAreaText = CLEANING.getText(occupationArea, context)
            }
            GARDENING.ordinal -> {
                occupationAreaImage = context.getDrawable(GARDENING.getImage())!!
                occupationAreaText = GARDENING.getText(occupationArea, context)
            }
            CONSTRUCTION.ordinal -> {
                occupationAreaImage = context.getDrawable(CONSTRUCTION.getImage())!!
                occupationAreaText = CONSTRUCTION.getText(occupationArea, context)
            }
            ELECTRIC.ordinal -> {
                occupationAreaImage = context.getDrawable(ELECTRIC.getImage())!!
                occupationAreaText = ELECTRIC.getText(occupationArea, context)
            }
            else -> {
                occupationAreaImage = context.getDrawable(PLUMBING.getImage())!!
                occupationAreaText = PLUMBING.getText(occupationArea, context)
            }
        }
    }
}
package com.slicedwork.slicedwork.presentation.viewholder

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.slicedwork.slicedwork.databinding.ItemCompletedWorksBinding
import com.slicedwork.slicedwork.domain.model.Vacancy
import com.slicedwork.slicedwork.util.enumerator.OccupationAreaEnum.*
import com.slicedwork.slicedwork.util.enumerator.VacancyStatusEnum.*

class CompletedWorksViewHolder(
    private val binding: ItemCompletedWorksBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(occupationArea: Int, vacancies: List<Vacancy>, context: Context) {
        when (occupationArea) {
            PAINTING.ordinal -> PAINTING.run {
                hideUpLine()
                setProps(
                    getText(context), getImage(context), getCompletedWorksList(
                        vacancies,
                        ordinal
                    )
                )
            }
            CLEANING.ordinal -> CLEANING.run {
                setProps(
                    getText(context),
                    getImage(context),
                    getCompletedWorksList(vacancies, ordinal)
                )
            }
            GARDENING.ordinal -> GARDENING.run {
                setProps(
                    getText(context),
                    getImage(context),
                    getCompletedWorksList(vacancies, ordinal)
                )
            }
            CONSTRUCTION.ordinal -> CONSTRUCTION.run {
                setProps(
                    getText(context),
                    getImage(context),
                    getCompletedWorksList(vacancies, ordinal)
                )
            }
            ELECTRIC.ordinal -> ELECTRIC.run {
                setProps(
                    getText(context),
                    getImage(context),
                    getCompletedWorksList(vacancies, ordinal)
                )
            }
            else -> PLUMBING.run {
                setProps(
                    getText(context),
                    getImage(context),
                    getCompletedWorksList(vacancies, ordinal)
                )
            }
        }
    }

    private fun getCompletedWorksList(vacancies: List<Vacancy>, occupationArea: Int): List<Vacancy> =
        vacancies.filter { vacancy -> vacancy.occupationArea == occupationArea && vacancy.status == FINISHED.ordinal}

    private fun hideUpLine() {
        binding.viewHorizontal.visibility = View.GONE
    }

    private fun setProps(text: String, image: Drawable?, completedWorksList: List<Vacancy>?) {
        binding.run {
            ivOccupationArea.setImageDrawable(image)
            tvOccupationArea.text = text
            tvAppliedQtt.text = completedWorksList?.size.toString()
        }
    }
}
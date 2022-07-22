package com.slicedwork.slicedwork.presentation.viewholder

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.slicedwork.slicedwork.R
import com.slicedwork.slicedwork.databinding.ItemVacancyBinding
import com.slicedwork.slicedwork.domain.model.Vacancy
import com.slicedwork.slicedwork.util.OccupationAreaUtil
import com.slicedwork.slicedwork.util.enumerator.OccupationAreaEnum.*
import com.slicedwork.slicedwork.util.enumerator.VacancyStatusEnum.*

class VacancyViewHolder(
    private val binding: ItemVacancyBinding,
    private val onItemClickListener: (vacancy: Vacancy) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var occupationAreaImage: Drawable
    private lateinit var occupationAreaText: String

    fun bind(vacancy: Vacancy, context: Context) {
        vacancy.run {
            val cityState = "$city - $state"
            val idWithHashtag = "#$id"

            binding.run {

                setOccupationAreaProps(occupationArea, context)
                tvId.text = idWithHashtag
                tvOccupationArea.text = occupationAreaText
                tvTask.text = task
                tvCityState.text = cityState
                if (Firebase.auth.uid == userId) {
                    val statusDrawable = getStatusDrawable(status, context)
                    ivStatus.setImageDrawable(statusDrawable)

                }
                else {
                    ivStatus.visibility = View.GONE
                }

                root.setOnClickListener {
                    onItemClickListener.invoke(vacancy)
                }
            }
        }
    }

    private fun getStatusDrawable(status: Int, context: Context): Drawable? = when (status) {
            OPENED.ordinal -> context.getDrawable(R.drawable.ic_opened_door)
            CLOSED.ordinal -> context.getDrawable(R.drawable.ic_closed_door)
            else -> context.getDrawable(R.drawable.ic_correct)
        }

    private fun setOccupationAreaProps(occupationArea: Int, context: Context) {
        occupationAreaImage = OccupationAreaUtil.getOccupationAreaDrawable(occupationArea, context)!!
        occupationAreaText = OccupationAreaUtil.getOccupationAreaString(occupationArea, context)
    }
}
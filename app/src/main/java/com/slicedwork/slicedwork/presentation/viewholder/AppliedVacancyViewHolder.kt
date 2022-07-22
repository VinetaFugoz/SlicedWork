package com.slicedwork.slicedwork.presentation.viewholder

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.slicedwork.slicedwork.R
import com.slicedwork.slicedwork.databinding.ItemVacancyBinding
import com.slicedwork.slicedwork.domain.model.Candidate
import com.slicedwork.slicedwork.domain.model.Vacancy
import com.slicedwork.slicedwork.util.OccupationAreaUtil
import com.slicedwork.slicedwork.util.enumerator.CandidateStatusEnum.*
import com.slicedwork.slicedwork.util.enumerator.VacancyStatusEnum

class AppliedVacancyViewHolder(
    private val binding: ItemVacancyBinding,
    private val onItemClickListener: (vacancy: Vacancy) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var appliedImage: Drawable
    private lateinit var occupationAreaImage: Drawable
    private lateinit var appliedText: String
    private lateinit var occupationAreaText: String

    fun bind(pair: Pair<Vacancy, Candidate>, context: Context) {
        val vacancy = pair.first
        val candidate = pair.second

        vacancy.run {
            val cityState = "$city - $state"
            val idWithHashtag = "#$id"

            binding.run {

                setOccupationAreaProps(occupationArea, context)
                tvId.text = idWithHashtag
                tvOccupationArea.text = occupationAreaText
                tvTask.text = task
                tvCityState.text = cityState
                val statusDrawable = getStatusDrawable(candidate.status, context)
                ivStatus.setImageDrawable(statusDrawable)

                root.setOnClickListener {
                    onItemClickListener.invoke(vacancy)
                }
            }
        }
    }

    private fun getStatusDrawable(status: Int, context: Context): Drawable? = when (status) {
        IN_WAITING.ordinal -> context.getDrawable(R.drawable.ic_hourglass)
        APPROVED.ordinal -> context.getDrawable(R.drawable.ic_right)
        else -> context.getDrawable(R.drawable.ic_wrong)
    }

    private fun setOccupationAreaProps(occupationArea: Int, context: Context) {
        occupationAreaImage = OccupationAreaUtil.getOccupationAreaDrawable(occupationArea, context)!!
        occupationAreaText = OccupationAreaUtil.getOccupationAreaString(occupationArea, context)
    }
}
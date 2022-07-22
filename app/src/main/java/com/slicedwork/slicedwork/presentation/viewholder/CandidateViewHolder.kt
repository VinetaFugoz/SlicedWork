package com.slicedwork.slicedwork.presentation.viewholder

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.slicedwork.slicedwork.R
import com.slicedwork.slicedwork.databinding.ItemCandidateBinding
import com.slicedwork.slicedwork.domain.model.Candidate
import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.util.enumerator.CandidateStatusEnum.*

class CandidateViewHolder(
    private val binding: ItemCandidateBinding,
    private val statusClickListener: (document: String, field: String, value: Int) -> Unit,
    private val approvedOneId: String? = null
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(usCandidate: Pair<User, Candidate>, context: Context) = binding.run {
        val user = usCandidate.first
        val candidate = usCandidate.second

        val fullname = "${user.firstName} ${user.lastName}"

        Glide.with(itemView).load(user.picture).circleCrop().into(incUser.ivUserPicture)
        incUser.tvName.text = fullname
        incUser.tvUsername.text = user.username

        ivStatus.setImageDrawable(
            ContextCompat.getDrawable(
                context, when (candidate.status) {
                    IN_WAITING.ordinal -> R.drawable.ic_hourglass
                    APPROVED.ordinal -> R.drawable.ic_right
                    else -> R.drawable.ic_wrong
                }
            )
        )

        if (approvedOneId != candidate.id && approvedOneId != null) {
            btnApprove.isEnabled = false
            btnDisapprove.isEnabled = false
        } else if (candidate.status == APPROVED.ordinal) {
            btnApprove.isEnabled = false
            btnDisapprove.isEnabled = true
        } else if (candidate.status == DISAPPROVED.ordinal) {
            btnApprove.isEnabled = true
            btnDisapprove.isEnabled = false
        }

        btnApprove.setOnClickListener {
            statusClickListener.invoke(candidate.id, "status", APPROVED.ordinal)
        }

        btnDisapprove.setOnClickListener {
            statusClickListener.invoke(candidate.id, "status", DISAPPROVED.ordinal)
        }
    }
}
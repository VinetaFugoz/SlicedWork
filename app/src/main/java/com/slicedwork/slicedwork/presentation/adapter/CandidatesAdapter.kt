package com.slicedwork.slicedwork.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.slicedwork.slicedwork.databinding.ItemCandidateBinding
import com.slicedwork.slicedwork.domain.model.Candidate
import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.domain.model.Vacancy
import com.slicedwork.slicedwork.presentation.viewholder.CandidateViewHolder

class CandidatesAdapter(
    private val usCandidates: List<Pair<User, Candidate>>,
    private val context: Context,
    private val approvedOneId: String? = null,
    private val statusClickListener: (document: String, field: String, value: Int) -> Unit
) :
    RecyclerView.Adapter<CandidateViewHolder>() {

    private lateinit var binding: ItemCandidateBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CandidateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemCandidateBinding.inflate(inflater, parent, false)
        return CandidateViewHolder(binding, statusClickListener, approvedOneId)
    }

    override fun onBindViewHolder(holder: CandidateViewHolder, position: Int) {
        holder.bind(usCandidates[position], context)
    }

    override fun getItemCount(): Int {
        return usCandidates.size
    }
}
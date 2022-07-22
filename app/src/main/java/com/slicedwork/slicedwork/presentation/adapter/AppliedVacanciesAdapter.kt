package com.slicedwork.slicedwork.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.slicedwork.slicedwork.databinding.ItemVacancyBinding
import com.slicedwork.slicedwork.domain.model.Candidate
import com.slicedwork.slicedwork.domain.model.Vacancy
import com.slicedwork.slicedwork.presentation.viewholder.AppliedVacancyViewHolder
import com.slicedwork.slicedwork.presentation.viewholder.VacancyViewHolder

class AppliedVacanciesAdapter(
    private val vacancies: List<Pair<Vacancy, Candidate>>,
    private val context: Context,
    private val onItemClickListener: (vacancy: Vacancy) -> Unit
) :
    RecyclerView.Adapter<AppliedVacancyViewHolder>() {

    private lateinit var binding: ItemVacancyBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppliedVacancyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemVacancyBinding.inflate(inflater, parent, false)
        return AppliedVacancyViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: AppliedVacancyViewHolder, position: Int) {
        holder.bind(vacancies[position], context)
    }

    override fun getItemCount(): Int {
        return vacancies.size
    }
}
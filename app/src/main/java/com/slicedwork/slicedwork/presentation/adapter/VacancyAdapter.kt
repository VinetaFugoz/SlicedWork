package com.slicedwork.slicedwork.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.slicedwork.slicedwork.databinding.ItemVacancyBinding
import com.slicedwork.slicedwork.domain.model.Vacancy
import com.slicedwork.slicedwork.generated.callback.OnClickListener
import com.slicedwork.slicedwork.presentation.viewholder.VacancyViewHolder

class VacancyAdapter(
    private val vacancies: List<Vacancy>,
    private val context: Context,
    private val onItemClickListener: (vacancy: Vacancy) -> Unit
) :
    RecyclerView.Adapter<VacancyViewHolder>() {

    private lateinit var binding: ItemVacancyBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemVacancyBinding.inflate(inflater, parent, false)
        return VacancyViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) {
        holder.bind(vacancies[position], context)
    }

    override fun getItemCount(): Int {
        return vacancies.size
    }
}
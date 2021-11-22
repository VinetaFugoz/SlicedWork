package com.slicedwork.slicedwork.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.slicedwork.slicedwork.R
import com.slicedwork.slicedwork.data.model.Vacancy
import com.slicedwork.slicedwork.databinding.ItemVacancyBinding
import com.slicedwork.slicedwork.util.temporary.getRange

class VacanciesAdapter(
    private val vacancies: List<Vacancy>,
    private val onItemCLickListener: (vacancy: Vacancy, view: View) -> Unit
) : RecyclerView.Adapter<VacanciesAdapter.VacancyViewHolder>() {

    private lateinit var binding: ItemVacancyBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemVacancyBinding.inflate(inflater, parent, false)
        return VacancyViewHolder(binding, onItemCLickListener)
    }

    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) {
        holder.bind(vacancies[position])
    }

    override fun getItemCount(): Int {
        return vacancies.size
    }

    inner class VacancyViewHolder(
        private val binding: ItemVacancyBinding,
        private val onItemCLickListener: (vacancy: Vacancy, view: View) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(vacancy: Vacancy) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(vacancy.urlImage)
                    .error(R.drawable.ic_launcher_background)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(ivVacancy)

                tvOccupationArea.text = vacancy.occupationArea
                tvTask.text = vacancy.task
                tvCity.text = vacancy.city
                tvRange.text = vacancy.postalCode?.let { getRange(it) }
                tvPrice.text = vacancy.price

                root.setOnClickListener { view ->
                    onItemCLickListener.invoke(vacancy, view)
                }
            }
        }
    }
}

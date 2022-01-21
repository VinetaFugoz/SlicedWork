package com.slicedwork.slicedwork.presentation.announcedvacancies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.slicedwork.slicedwork.domain.model.Vacancy
import com.slicedwork.slicedwork.databinding.ItemAnnouncedVacancyBinding
import com.slicedwork.slicedwork.util.getImgOccupationArea

class AnnouncedVacanciesAdapter(
    private val vacancies: List<Vacancy>,
    private val onItemCLickListener: (vacancy: Vacancy, view: View) -> Unit
) : RecyclerView.Adapter<AnnouncedVacanciesAdapter.AnnouncedVacancyViewHolder>() {

    private lateinit var binding: ItemAnnouncedVacancyBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncedVacancyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemAnnouncedVacancyBinding.inflate(inflater, parent, false)
        return AnnouncedVacancyViewHolder(binding, onItemCLickListener)
    }

    override fun onBindViewHolder(holder: AnnouncedVacancyViewHolder, position: Int) {
        holder.bind(vacancies[position])
    }

    override fun getItemCount(): Int {
        return vacancies.size
    }

    inner class AnnouncedVacancyViewHolder(
        private val binding: ItemAnnouncedVacancyBinding,
        private val onItemCLickListener: (vacancy: Vacancy, view: View) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(vacancy: Vacancy) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(vacancy.urlImage)
                    .error(getImgOccupationArea(vacancy.occupationArea))
                    .placeholder(getImgOccupationArea(vacancy.occupationArea))
                    .into(ivOccupationArea)

                tvOccupationArea.text = vacancy.occupationArea
                tvTask.text = vacancy.task
                tvCity.text = vacancy.city

                root.setOnClickListener { view ->
                    onItemCLickListener.invoke(vacancy, view)
                }
            }
        }
    }
}



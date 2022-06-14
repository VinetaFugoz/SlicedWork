package com.slicedwork.slicedwork.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.slicedwork.slicedwork.databinding.ItemCompletedWorksBinding
import com.slicedwork.slicedwork.domain.model.Vacancy
import com.slicedwork.slicedwork.presentation.viewholder.CompletedWorksViewHolder

class CompletedWorksAdapter(
    private val occupationAreaList: List<Int>,
    private val vacancies: List<Vacancy>,
    private val context: Context,
) : RecyclerView.Adapter<CompletedWorksViewHolder>() {

    private lateinit var binding: ItemCompletedWorksBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompletedWorksViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemCompletedWorksBinding.inflate(inflater, parent, false)
        return CompletedWorksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CompletedWorksViewHolder, position: Int) {
        holder.bind(occupationAreaList[position], vacancies, context)
    }

    override fun getItemCount(): Int = occupationAreaList.size
}
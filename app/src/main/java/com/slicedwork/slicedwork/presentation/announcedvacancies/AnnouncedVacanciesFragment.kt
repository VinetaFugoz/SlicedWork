package com.slicedwork.slicedwork.presentation.announcedvacancies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.slicedwork.slicedwork.databinding.FragmentAnnouncedVacanciesBinding
import com.slicedwork.slicedwork.util.temporary.getVacancies

class AnnouncedVacanciesFragment : Fragment() {

    private lateinit var binding: FragmentAnnouncedVacanciesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnnouncedVacanciesBinding.inflate(inflater, container, false)

        with(binding.rvAnnouncedVacancies) {
            adapter = AnnouncedVacanciesAdapter(getVacancies(), onItemCLickListener = { vacancy, view ->
            })
            layoutManager = LinearLayoutManager(this.context)
        }

        return binding.root
    }
}
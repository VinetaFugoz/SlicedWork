package com.slicedwork.slicedwork.presentation.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.slicedwork.slicedwork.domain.model.Vacancy
import com.slicedwork.slicedwork.databinding.FragmentHomeBinding
import com.slicedwork.slicedwork.util.temporary.getVacancies

class HomeFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        with(binding.rvVacancies) {
            adapter = HomeVacanciesAdapter(getVacancies(), onItemCLickListener = { vacancy, view ->
                navigateToVacancy(vacancy, view)
            })
            layoutManager = LinearLayoutManager(this.context)
        }


        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.btnContacts.setOnClickListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        ) || super.onOptionsItemSelected(item)
    }

    private fun navigateToVacancy(vacancy: Vacancy, view: View) {
        view.findNavController()
            .navigate(HomeFragmentDirections.actionHomeFragmentToVacancyFragment(vacancy))
    }

    override fun onClick(view: View) {
        when (view) {
            binding.btnContacts -> {
                view.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLogin())
            }

        }
    }
}
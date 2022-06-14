package com.slicedwork.slicedwork.presentation.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.slicedwork.slicedwork.R
import com.slicedwork.slicedwork.databinding.FragmentProfileBinding
import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.domain.model.Vacancy
import com.slicedwork.slicedwork.presentation.adapter.CompletedWorksAdapter
import com.slicedwork.slicedwork.presentation.viewmodel.ProfileViewModel
import com.slicedwork.slicedwork.util.OccupationAreaUtil.occupationAreaList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private var userArgs: Any? = null
    private var user: User? = null
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setProps()
        toggleProfile()
        setEvents()
        setObservers()
    }

    private fun setProps() {
        userArgs = arguments?.get("user")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.log_out -> {
                logOutEvent()
            }
        }
        return true
    }

    private fun toggleProfile() {
        viewModel.getVacancies()
        if (iAmOwner(userArgs)) {
            viewModel.getUser(Firebase.auth.currentUser!!.uid)
            setHasOptionsMenu(true)
        } else {
            binding.btnWpp.visibility = View.VISIBLE
            user = userArgs as User
            setUserProps()
        }
    }

    private fun iAmOwner(userArgs: Any?): Boolean = userArgs == null

    private fun setUserProps() {
        setVisibility()
        binding.run {
            Glide.with(requireContext()).load(user?.picture).circleCrop().into(ivPicture)

            tvName.text = "${user?.firstName} ${user?.lastName}"
            tvUsername.text = user?.username
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.profile_menu, menu)
    }

    private fun setEvents() {
        binding.btnWpp.setOnClickListener { wppEvent() }
    }

    private fun setObservers() {
        viewModel.userLiveData.observe(viewLifecycleOwner) { user ->
            setVisibility()
            this@ProfileFragment.user = user
            setUserProps()
        }

        viewModel.vacanciesLiveData.observe(viewLifecycleOwner) { vacancies ->
            setOccupationAreaListInRecycler(occupationAreaList, vacancies)
        }
    }

    private fun setVisibility() {
        binding.run {
            progressBar.visibility = View.GONE
            ivPicture.visibility = View.VISIBLE
            cdDetails.visibility = View.VISIBLE
        }
    }

    private fun setOccupationAreaListInRecycler(
        occupationAreaList: List<Int>,
        vacancies: List<Vacancy>
    ) {
        binding.rvCompletedWorks.run {
            layoutManager = LinearLayoutManager(this@ProfileFragment.requireContext())
            adapter = CompletedWorksAdapter(occupationAreaList, vacancies, requireContext())
        }
    }

    private fun wppEvent() {
        val wppIntent = Intent(Intent.ACTION_VIEW)
            .setData(Uri.parse("https://api.whatsapp.com/send?phone=+55${user?.phoneNumber}"))

        startActivity(wppIntent)
    }

    private fun logOutEvent() {
        Firebase.auth.signOut()
        findNavController().navigateUp()
    }
}
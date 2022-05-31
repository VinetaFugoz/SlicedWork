package com.slicedwork.slicedwork.presentation.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.slicedwork.slicedwork.R
import com.slicedwork.slicedwork.databinding.FragmentProfileBinding
import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.domain.model.Vacancy
import com.slicedwork.slicedwork.presentation.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var _binding: FragmentProfileBinding
    private var _user: User? = null
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setProps(inflater)
        initializeIncludes()

        viewModel.userViewModel.observe(viewLifecycleOwner) { user ->
            _user = user
            setUserViews()
        }
        return _binding.root
    }

    override fun onResume() {
        super.onResume()
        setListeners()
    }

    private fun setListeners() {
        _binding.btnWpp.setOnClickListener { wppEvent() }
    }

    private fun wppEvent() {
        val wppIntent = Intent(Intent.ACTION_VIEW)
            .setData(Uri.parse("https://api.whatsapp.com/send?phone=+55${_user?.phoneNumber}"))

        startActivity(wppIntent)
    }

    private fun setUserViews() {
        _binding.run {
            Glide.with(requireContext()).load(_user?.picture).circleCrop().into(ivPicture)

            tvName.text = "${_user?.firstName} ${_user?.lastName}"
            tvUsername.text = _user?.username
        }
    }

    private fun setProps(inflater: LayoutInflater) {
        _binding = FragmentProfileBinding.inflate(inflater)
        val userArgs = arguments?.get("user")
        if (userArgs == null) {
            _user = userArgs
            setHasOptionsMenu(true)
        } else _user = userArgs as User

        if (_user == null) viewModel.getUser(Firebase.auth.currentUser!!.uid)
        else (setUserViews())
    }

    private fun initializeIncludes() {
        _binding.run {
            incPainting.run {
                viewHorizontal.visibility = View.GONE
                ivOccupationArea.setImageDrawable(getOccupationAreaDrawable(R.drawable.ic_painting))
                tvOccupationArea.text = requireContext().getText(R.string.painting)
            }
            incCleaning.run {
                ivOccupationArea.setImageDrawable(getOccupationAreaDrawable(R.drawable.ic_cleaning))
                tvOccupationArea.text = requireContext().getText(R.string.cleaning)
            }
            incGardening.run {
                ivOccupationArea.setImageDrawable(getOccupationAreaDrawable(R.drawable.ic_gardening))
                tvOccupationArea.text = requireContext().getText(R.string.gardening)
            }
            incConstruction.run {
                ivOccupationArea.setImageDrawable(getOccupationAreaDrawable(R.drawable.ic_construction))
                tvOccupationArea.text = requireContext().getText(R.string.construction)
            }
            incElectric.run {
                ivOccupationArea.setImageDrawable(getOccupationAreaDrawable(R.drawable.ic_electric))
                tvOccupationArea.text = requireContext().getText(R.string.electric)
            }
            incPlumbing.run {
                ivOccupationArea.setImageDrawable(getOccupationAreaDrawable(R.drawable.ic_plumbing))
                tvOccupationArea.text = requireContext().getText(R.string.plumbing)
            }
        }
    }

    private fun getOccupationAreaDrawable(drawable: Int) =
        ContextCompat.getDrawable(requireContext(), drawable)

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.profile_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.log_out -> {
                logOutEvent()
            }
        }
        return true
    }

    private fun logOutEvent() {
        Firebase.auth.signOut()
        findNavController().navigateUp()
    }
}
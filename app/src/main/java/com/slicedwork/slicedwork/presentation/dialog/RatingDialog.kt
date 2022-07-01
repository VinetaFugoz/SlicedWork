package com.slicedwork.slicedwork.presentation.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.slicedwork.slicedwork.databinding.DialogRatingBinding
import com.slicedwork.slicedwork.domain.model.Rating
import com.slicedwork.slicedwork.presentation.viewmodel.RatingViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class RatingDialog : BottomSheetDialogFragment() {

    private lateinit var ratingId: String
    private lateinit var userId: String
    private lateinit var binding: DialogRatingBinding
    private val viewModel: RatingViewModel by viewModels()
    private var isThereRating: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogRatingBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        userId = arguments?.get("userId") as String
        viewModel.getRatingUseCase(FirebaseAuth.getInstance().currentUser!!.uid, userId)
        setEvents()
        setObservers()
    }

    private fun setObservers() {
        viewModel.run {
            ratingLiveData.observe(viewLifecycleOwner) { rating ->
                if (rating != null) {
                    isThereRating = true
                    ratingId = rating.id
                    binding.ratingBar.rating = rating.rating.toFloat()
                }
            }

            isRegisteredLiveData.observe(viewLifecycleOwner) { isRegistered ->
                if (isRegistered) goBackToProfile()
            }

            isUpdatedLiveData.observe(viewLifecycleOwner) { isUpdated ->
                if (isUpdated) goBackToProfile()
            }
        }
    }

    private fun goBackToProfile() {
        findNavController().navigateUp()
    }

    private fun getRating(): Rating = Rating(
        id = UUID.randomUUID().toString(),
        fromUserId = FirebaseAuth.getInstance().currentUser!!.uid,
        toUserId = userId,
        rating = binding.ratingBar.rating.toDouble()
    )

    private fun setEvents() {
        binding.btnCancel.setOnClickListener { cancelEvent() }
        binding.btnFinish.setOnClickListener { finishEvent() }
    }

    private fun cancelEvent() {
        goBackToProfile()
    }

    private fun finishEvent() {
        viewModel.run {
            if (isThereRating) updateRatingUseCase(ratingId, binding.ratingBar.rating.toDouble())
            else registerRatingUseCase(getRating())
        }
    }
}
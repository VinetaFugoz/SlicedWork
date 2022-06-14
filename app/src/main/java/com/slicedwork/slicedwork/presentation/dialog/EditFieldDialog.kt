package com.slicedwork.slicedwork.presentation.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.slicedwork.slicedwork.R
import com.slicedwork.slicedwork.databinding.DialogEditFieldBinding
import com.slicedwork.slicedwork.presentation.viewmodel.EditFieldViewModel
import com.slicedwork.slicedwork.util.enumerator.CollectionEnum
import com.slicedwork.slicedwork.util.enumerator.CollectionEnum.*
import com.slicedwork.slicedwork.util.enumerator.FieldEnum
import com.slicedwork.slicedwork.util.enumerator.FieldEnum.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditFieldDialog : BottomSheetDialogFragment() {

    private lateinit var collection: CollectionEnum
    private lateinit var document: String
    private lateinit var field: FieldEnum
    private lateinit var initialValue: String
    private lateinit var value: String
    private lateinit var binding: DialogEditFieldBinding
    private var isMenu: Boolean = false
    private val viewModel: EditFieldViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogEditFieldBinding.inflate(inflater)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setArgs()
        setEvents()
        setObservers()
        chooseInputField()
    }

    private fun setArgs() {
        collection = arguments?.get("collection") as CollectionEnum
        document = arguments?.get("document") as String
        field = arguments?.get("field") as FieldEnum
        initialValue = arguments?.get("initialValue") as String
    }

    private fun setEvents() {
        binding.run {
            btnFinish.setOnClickListener { finishEvent() }
            btnCancel.setOnClickListener { cancelEvent() }
            actvField.setOnItemClickListener { _, _, i, _ ->
               fieldMenuEvent(i)
            }
        }
    }

    private fun setObservers() {
        viewModel.updatedLiveData.observe(viewLifecycleOwner) { updated ->
            if (updated) goBackWithArguments(updated)
        }
    }

    private fun chooseInputField() {
        when (field) {
            STATUS -> setMenuFieldProps(R.array.vacancy_details_status)
            else -> setFieldProps()
        }
    }

    private fun setMenuFieldProps(resourceArray: Int) {
        binding.run {
            isMenu = true
            tilField.visibility = View.GONE
            val fieldArray = resources.getStringArray(resourceArray)
            actvField.setText(fieldArray[initialValue.toInt() - 1])
            val fieldAdapter = ArrayAdapter(
                this@EditFieldDialog.requireContext(),
                R.layout.support_simple_spinner_dropdown_item, fieldArray
            )
            actvField.setAdapter(fieldAdapter)
        }
    }

    private fun setFieldProps() {
        binding.run {
            tilFieldMenu.visibility = View.GONE
            tietField.setText(initialValue)
        }
    }

    private fun finishEvent() {
        if (collection == VACANCY) viewModel.updateVacancy(isMenu, document, field, value)
    }

    private fun cancelEvent() {
        findNavController().navigateUp()
    }

    private fun fieldMenuEvent(i: Int) {
        val position = i + 1
        value = position.toString()
        binding.btnFinish.isEnabled = true
    }

    private fun goBackWithArguments(updated: Boolean) {
        findNavController().run {
            previousBackStackEntry?.savedStateHandle?.set("updated", updated)
            navigateUp()
        }
    }
}
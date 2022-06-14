package com.slicedwork.slicedwork.presentation.viewmodel

import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slicedwork.slicedwork.domain.usecase.vacancy.UpdateVacancyUseCase
import com.slicedwork.slicedwork.util.enumerator.FieldEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditFieldViewModel @Inject constructor(private val updateVacancyUseCase: UpdateVacancyUseCase) : ViewModel(){
    var updatedLiveData: MutableLiveData<Boolean> = MutableLiveData()

    init {
        updatedLiveData.value = false
    }

    fun updateVacancy(isMenu: Boolean, document: String, field: FieldEnum, value: String) = viewModelScope.launch {
            updateVacancyUseCase.invoke(isMenu, document, field, value) { updated ->
                updatedLiveData.value = updated
            }
    }

}
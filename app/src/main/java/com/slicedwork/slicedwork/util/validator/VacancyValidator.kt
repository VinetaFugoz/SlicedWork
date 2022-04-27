package com.slicedwork.slicedwork.util.validator

class VacancyValidator: Validator() {
    fun validateTask(task: String): Boolean {
        return !isEmpty(task)
    }

    fun validateOccupationArea(occupationArea: String): Boolean {
        return !isEmpty(occupationArea)
    }

    fun validatePrice(price: String): Boolean {
        return !isEmpty(price)
    }
}
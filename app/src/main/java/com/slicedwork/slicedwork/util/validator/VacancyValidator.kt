package com.slicedwork.slicedwork.util.validator

class VacancyValidator: Validator() {
    //Details
    fun validateTask(task: String): Boolean = !isEmpty(task)
    fun validateOccupationArea(occupationArea: String): Boolean = !isEmpty(occupationArea)
    fun validatePrice(price: String): Boolean = !isEmpty(price)

    //Address
    fun validateCountry(country: String): Boolean = !isEmpty(country)
    fun validateState(state: String): Boolean = !isEmpty(state)
    fun validateCity(city: String): Boolean = !isEmpty(city)
    fun validateNeighborhood(neighborhood: String): Boolean = !isEmpty(neighborhood)
    fun validatePostalCode(postalCode: String): Boolean = !isEmpty(postalCode)
    fun validateStreet(street: String): Boolean = !isEmpty(street)
    fun validateNumber(number: String): Boolean = !isEmpty(number)
}
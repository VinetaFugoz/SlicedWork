package presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.slicedwork.slicedwork.util.validator.UserValidator
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun `when i get a right email and password it should be true`() {
        val emailResult = UserValidator().validateEmail("email@email.com")
        val passwordResult = UserValidator().validatePassword("123456")

        assertEquals(true, emailResult && passwordResult)
    }

}
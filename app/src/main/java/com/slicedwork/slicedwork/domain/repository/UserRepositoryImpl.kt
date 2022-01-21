package com.slicedwork.slicedwork.domain.repository

import com.google.firebase.auth.FirebaseAuth
import com.slicedwork.slicedwork.data.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class UserRepositoryImpl : UserRepository {

    lateinit var firebaseAuth: FirebaseAuth

    override fun loginUser(email: String, password: String) {
        firebaseAuth = FirebaseAuth.getInstance()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                firebaseAuth.signInWithEmailAndPassword(email, password).await()
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }

    override fun registerUser(email: String, password: String) {
        firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {

            }
    }
}
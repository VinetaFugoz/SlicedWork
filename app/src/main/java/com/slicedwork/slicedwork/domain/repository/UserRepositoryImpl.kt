package com.slicedwork.slicedwork.domain.repository

import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.slicedwork.slicedwork.data.repository.UserRepository
import com.slicedwork.slicedwork.domain.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class UserRepositoryImpl : UserRepository {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun loginUser(user: User) {
        firebaseAuth = FirebaseAuth.getInstance()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                firebaseAuth.signInWithEmailAndPassword(user.email, user.password)
                    .addOnCompleteListener {
                        Log.i("UserRepository", "User Logged")
                    }
                    .addOnFailureListener { exception ->
                        Log.e("UserRepository", "Exception on user login: ${exception.message.toString()}")
                    }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }

    override fun registerUser(user: User, uriImage: Uri?) {
        createUserAuthentication(user, uriImage)
    }

    private fun createUserAuthentication(user: User, uriImage: Uri?) {
        firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener {
                Log.i("UserRepository", it.result.toString())
                saveProfilePicture(user, uriImage)
            }.addOnFailureListener {
                Log.i("UserRepository", it.message.toString())
            }
    }

    private fun saveProfilePicture(user: User, uriImage: Uri?) {
        val filename = user.id
        val firebaseStorage =
            FirebaseStorage.getInstance().getReference("/images/profile_pictures/$filename")
        uriImage?.let { uri ->
            firebaseStorage.putFile(uri).addOnSuccessListener {
                Log.i("UserRepository", "Profile picture Saved")
                firebaseStorage.downloadUrl.addOnSuccessListener { uri ->
                    Log.i("UserRepository", "ProfilePic Uri = $uri")
                    user.uriImage = uri.toString()
                    saveUser(user)
                }
            }.addOnFailureListener { exception ->
                Log.e("UserRepository", "Exception on profile picture save: ${exception.message.toString()}")
            }
        }
    }

    private fun saveUser(user: User) {
        FirebaseFirestore.getInstance().collection("user").document(user.id)
            .set(user)
            .addOnSuccessListener {
                Log.e("UserRepository", "User saved")
            }.addOnFailureListener { exception ->
                Log.e("UserRepository", "Exception on user save: ${exception.message.toString()}")
            }
    }
}
package com.slicedwork.slicedwork.data.datasource

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.slicedwork.slicedwork.domain.model.User
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore,
) : UserDataSource {
    private lateinit var user: User

    override suspend fun registerUser(user: User) {
        this.user = user
        authUser()
    }

    override suspend fun loginUser(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            it.user?.uid
        }.addOnFailureListener {
            it.message.toString()
        }
    }

    private fun authUser() {
        firebaseAuth.createUserWithEmailAndPassword(user.email, user.password).addOnSuccessListener {
            createUserCollection()
        }.addOnFailureListener {
            it.message.toString()
        }
    }

    private fun createUserCollection() {
        user.uuid = firebaseAuth.currentUser!!.uid
        firebaseFirestore.collection("user")
            .document(user.uuid)
            .set(user).addOnSuccessListener {
                storagePicture()
            }
    }

    private fun storagePicture() {
        val storageReference = FirebaseStorage.getInstance().getReference("/images/user_pictures/${user.uuid}")
        storageReference.putFile(Uri.parse(user.picture))
    }
}
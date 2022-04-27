package com.slicedwork.slicedwork.data.datasource

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.slicedwork.slicedwork.domain.model.User
import javax.inject.Inject

class FirebaseUserDataSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore,
) : UserDataSource {
    override suspend fun registerUser(user: User) {
        authUser(user)
        storagePicture(user)
        createUserCollection(user)
    }

    override suspend fun loginUser(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
    }

    private fun authUser(user: User) {
        firebaseAuth.createUserWithEmailAndPassword(user.email, user.password)
    }

    private fun storagePicture(user: User) {
        val storageReference = FirebaseStorage.getInstance().getReference("/images/${user.uuid}")
        storageReference.putFile(Uri.parse(user.picture))
    }

    private fun createUserCollection(user: User) {
        firebaseFirestore.collection("user")
            .document(user.uuid)
            .set(user)
    }
}
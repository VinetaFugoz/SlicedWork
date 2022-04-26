package com.slicedwork.slicedwork.data.datasource

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.slicedwork.slicedwork.domain.model.User
import javax.inject.Inject

class FirebaseUserDataSourceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore,
    private var storageReference: StorageReference,
) : UserDataSource {
    override suspend fun registerUser(user: User): Boolean {
        authUser(user)
        storagePicture(user)
        createUserCollection(user)

        return true
    }

    private fun authUser(user: User) {
        firebaseAuth.createUserWithEmailAndPassword(user.email, user.password)
    }

    private fun storagePicture(user: User) {
        storageReference = FirebaseStorage.getInstance().getReference("/images/${user.uuid}")
        storageReference.putFile(Uri.parse(user.picture))
    }

    private fun createUserCollection(user: User) {
        firebaseFirestore.collection("user")
            .document(user.uuid)
            .set(user)
    }
}
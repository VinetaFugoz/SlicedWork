package com.slicedwork.slicedwork.data.datasource

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
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
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener { result ->
            result.user?.uid
        }.addOnFailureListener { exception ->
            exception.message.toString()
        }
    }

    override suspend fun getUser(userId: String, userCallBack: (User) -> Unit) {
        firebaseFirestore.collection("/user")
            .document(userId).get().addOnSuccessListener { snapshot ->
                val user = snapshot.toObject<User>()
                if (user != null) {
                    userCallBack(user)
                }
            }.addOnFailureListener { exception ->
                exception.message.toString()
            }
    }

    private fun authUser() {
        firebaseAuth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnSuccessListener { result ->
                result.user?.uid
                createUserCollection()
            }.addOnFailureListener {
                it.message.toString()
            }
    }

    private fun createUserCollection() {
        user.id = firebaseAuth.currentUser!!.uid
        firebaseFirestore.collection("user")
            .document(user.id)
            .set(user).addOnSuccessListener {
                storagePicture()
            }
    }

    private fun storagePicture() {
        val storageReference =
            FirebaseStorage.getInstance().getReference("/images/user_pictures/${user.id}")
        storageReference.putFile(Uri.parse(user.picture))
    }
}
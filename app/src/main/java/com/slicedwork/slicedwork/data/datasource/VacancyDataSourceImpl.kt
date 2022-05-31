package com.slicedwork.slicedwork.data.datasource

import android.net.Uri
import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.slicedwork.slicedwork.domain.model.Vacancy
import javax.inject.Inject

class VacancyDataSourceImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : VacancyDataSource {
    private lateinit var vacancy: Vacancy

    override suspend fun registerVacancy(vacancy: Vacancy) {
        this.vacancy = vacancy
        storagePicture()
    }

    override suspend fun getVacancies(vacancyCallback: (List<Vacancy>) -> Unit) {
        var vacancies: MutableList<Vacancy>

        firebaseFirestore.collection("/vacancy")
            .whereNotEqualTo("userId", Firebase.auth.currentUser!!.uid)
            .addSnapshotListener { snapshot, _ ->
                vacancies = mutableListOf()
                val documents: List<DocumentChange> = snapshot!!.documentChanges
                for (document in documents) {
                    val vacancy = document.document.toObject<Vacancy>()
                    vacancies.add(vacancy)
                }
                vacancyCallback(vacancies)
            }
    }

    private fun storagePicture() {
        val storageReference =
            FirebaseStorage.getInstance().getReference("/images/vacancy_pictures/${vacancy.id}")
        storageReference.putFile(Uri.parse(vacancy.picture)).addOnSuccessListener {
            storageReference.downloadUrl.addOnSuccessListener { uri ->
                vacancy.picture = uri.toString()
                createVacancyCollection()
            }.addOnFailureListener { exception ->
                val message = exception.message.toString()
                Log.i("VacancyData", message)
            }
        }
    }

    private fun createVacancyCollection() {
        firebaseFirestore.collection("vacancy")
            .document(vacancy.id)
            .set(vacancy)
    }
}
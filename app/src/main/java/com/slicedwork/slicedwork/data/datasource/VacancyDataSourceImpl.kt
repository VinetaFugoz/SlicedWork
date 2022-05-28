package com.slicedwork.slicedwork.data.datasource

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.storage.FirebaseStorage
import com.slicedwork.slicedwork.domain.model.Vacancy
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class VacancyDataSourceImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : VacancyDataSource {
    private lateinit var vacancy: Vacancy

    override suspend fun registerVacancy(vacancy: Vacancy) {
        this.vacancy = vacancy
        storagePicture()
    }

    override suspend fun getVacancies(vacancyCallBack: (List<Vacancy>) -> Unit) {
            var vacancies: MutableList<Vacancy> = mutableListOf()

            firebaseFirestore.collection("/vacancy").addSnapshotListener { snapshot, _ ->
                vacancies = mutableListOf()
                val documents: List<DocumentChange> = snapshot!!.documentChanges
                for (document in documents) {
                    val vacancy = document.document.toObject<Vacancy>()
                    vacancies.add(vacancy)
                }
                vacancyCallBack(vacancies)
            }

    }

    private fun storagePicture() {
        val storageReference =
            FirebaseStorage.getInstance().getReference("/images/vacancy_pictures/${vacancy.id}")
        storageReference.putFile(Uri.parse(vacancy.picture))
        storageReference.downloadUrl.addOnSuccessListener {
            vacancy.picture = it.toString()
            createVacancyCollection()
        }
    }

    private fun createVacancyCollection() {
        firebaseFirestore.collection("vacancy")
            .document(vacancy.id)
            .set(vacancy)
    }
}
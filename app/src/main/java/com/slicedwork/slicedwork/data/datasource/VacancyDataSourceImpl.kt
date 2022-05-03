package com.slicedwork.slicedwork.data.datasource

import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.slicedwork.slicedwork.domain.model.Vacancy
import javax.inject.Inject

class VacancyDataSourceImpl @Inject constructor(
   private val firebaseFirestore: FirebaseFirestore
): VacancyDataSource {
    override suspend fun registerVacancy(vacancy: Vacancy) {
        storagePicture(vacancy)
        createVacancyCollection(vacancy)
    }

    private fun storagePicture(vacancy: Vacancy) {
        val storageReference = FirebaseStorage.getInstance().getReference("/images/vacancy_pictures/${vacancy.id}")
        storageReference.putFile(Uri.parse(vacancy.picture))
    }

    private fun createVacancyCollection(vacancy: Vacancy) {
        firebaseFirestore.collection("vacancy")
            .document(vacancy.id)
            .set(vacancy)
    }
}
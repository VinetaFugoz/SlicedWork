package com.slicedwork.slicedwork.data.datasource

import android.net.Uri
import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.slicedwork.slicedwork.domain.model.Vacancy
import com.slicedwork.slicedwork.util.enumerator.FieldEnum
import javax.inject.Inject

class VacancyDataSourceImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : VacancyDataSource {
    private lateinit var vacancy: Vacancy

    override suspend fun registerVacancy(vacancy: Vacancy, vacancyCallback: (Boolean) -> Unit) {
        this.vacancy = vacancy
        val storageReference =
            FirebaseStorage.getInstance().getReference("/images/vacancy_pictures/${vacancy.id}")
        storageReference.putFile(Uri.parse(vacancy.picture)).addOnSuccessListener {
            storageReference.downloadUrl.addOnSuccessListener { uri ->
                vacancy.picture = uri.toString()
                firebaseFirestore.collection("vacancy")
                    .document(vacancy.id)
                    .set(vacancy).addOnSuccessListener {
                        vacancyCallback(true)
                    }
            }.addOnFailureListener { exception ->
                val message = exception.message.toString()
                Log.i("VacancyData", message)
            }
        }
    }

    override suspend fun getVacancies(
        isInHome: Boolean,
        status: Int?,
        userId: String,
        vacancyCallback: (List<Vacancy>) -> Unit
    ) {
        val query = firebaseFirestore.collection("/vacancy")
        if (isInHome) {
            query.whereNotEqualTo("userId", userId)
                .addSnapshotListener { snapshot, _ ->
                    vacancyCallback(convertSnapshot(snapshot))
                }
        } else {
            firebaseFirestore.collection("/vacancy")
                .whereEqualTo("userId", userId)
                .addSnapshotListener { snapshot, _ ->
                    vacancyCallback(convertSnapshot(snapshot))
                }
        }
    }

    override suspend fun updateVacancy(isMenu: Boolean, document: String, field: FieldEnum, value: String, vacancyCallback: (Boolean) -> Unit) {
        firebaseFirestore.collection("vacancy")
            .document(document)
            .update(field.getText(), if (isMenu) value.toInt() else value).addOnSuccessListener {
                vacancyCallback(true)
            }
    }

    override suspend fun deleteVacancy(vacancyId: String, vacancyCallback: (Boolean) -> Unit) {
        firebaseFirestore.collection("vacancy")
            .document(vacancyId)
            .delete().addOnSuccessListener {
                vacancyCallback(true)
            }
    }


    override suspend fun getVacancyById(vacancyId: String, vacancyCallback: (Vacancy) -> Unit) {
        firebaseFirestore.collection("vacancy")
            .document(vacancyId)
            .get().addOnSuccessListener { snapshot ->
                convertSnapshot(snapshot)?.let {
                    vacancyCallback(it)
                }
            }
    }

    private fun convertSnapshot(snapshot: QuerySnapshot?): List<Vacancy> {
        val vacancies: MutableList<Vacancy> = mutableListOf()
        val documents: List<DocumentChange> = snapshot!!.documentChanges
        for (document in documents) {
            val vacancy = document.document.toObject<Vacancy>()
            vacancies.add(vacancy)
        }
        return vacancies
    }

    private fun convertSnapshot(document: DocumentSnapshot?): Vacancy? =
        document?.toObject<Vacancy>()
}
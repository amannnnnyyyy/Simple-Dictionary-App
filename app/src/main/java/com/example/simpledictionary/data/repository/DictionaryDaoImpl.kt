package com.example.simpledictionary.data.repository

import android.util.Log
import com.example.simpledictionary.domain.db.DictionaryDao
import com.example.simpledictionary.domain.model.WordDetail
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DictionaryDaoImpl@Inject constructor(private val firestore: FirebaseFirestore): DictionaryDao {

    private val wordsCollection = firestore.collection("words")


    override suspend fun addWord(wordDetail: WordDetail) {
        wordDetail.word?.let { wordToBeSaved ->
            wordsCollection.document(wordToBeSaved).set(wordDetail).await()
        }
    }

    override suspend fun getWord(word: String): WordDetail? {
        val documentSnapshot = wordsCollection.document(word).get().await()
        return documentSnapshot.toObject(WordDetail::class.java)
    }

    override suspend fun getWords(): Flow<List<WordDetail>> = callbackFlow {
        val listenerRegistration = wordsCollection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                cancel(error.message ?: "Firestore error")
                return@addSnapshotListener
            }
            val users = snapshot?.documents?.mapNotNull { it.toObject(WordDetail::class.java) }
                ?: listOf<WordDetail>(WordDetail(null, null, null))

            if (users.isNotEmpty()){ trySend(users) }
        }
        awaitClose { listenerRegistration.remove() }
    }

}
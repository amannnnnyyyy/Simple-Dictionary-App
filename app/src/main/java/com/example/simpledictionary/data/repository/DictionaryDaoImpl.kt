package com.example.simpledictionary.data.repository

import android.util.Log
import com.example.simpledictionary.domain.db.DictionaryDao
import com.example.simpledictionary.domain.model.Entry
import com.example.simpledictionary.domain.model.WordDetail
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DictionaryDaoImpl@Inject constructor(private val firestore: FirebaseFirestore): DictionaryDao {

    private val wordsCollection = firestore.collection("words")


    override suspend fun addWord(wordDetail: WordDetail) {
        Log.i("FireStoreAdd", "Result: now in repo $wordDetail")

        wordDetail.word?.let {
            wordToBeSaved ->
            try {
                Log.i("FireStoreAdd", "we are using $wordToBeSaved")

                wordsCollection.document(wordToBeSaved).set(wordDetail).addOnSuccessListener {
                    Log.i("FireStoreAdd", "Result: successful")

                }.addOnFailureListener {
                    Log.i("FireStoreAdd", "Result: failed")

                }
            }catch (e: Exception){
                Log.i("FireStoreAdd", "Result: failed ${e.stackTrace}")
            }
        }
        Log.i("FireStoreAdd", "Result: done")

    }

    override suspend fun getWord(word: String): WordDetail? {
        val documentSnapshot = wordsCollection.document(word).get().await()

        return documentSnapshot.toObject(WordDetail::class.java)
    }

    override fun getWords(): Flow<List<WordDetail>> = channelFlow {
        val listenerRegistration = wordsCollection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                cancel("Error fetching words from Firestore", error)
                return@addSnapshotListener
            }

            if (snapshot != null) {
                val words = snapshot.documents.mapNotNull { document ->
                    try {
                        document.toObject(WordDetail::class.java)
                    } catch (e: Exception) {
                        Log.e("Database_fetched", "Error parsing document ${document.id}", e)
                        null
                    }
                }
                val result = trySendBlocking(words)
                if (result.isFailure) {
                    Log.w("DictionaryDaoImpl", "Failed to send words to flow", result.exceptionOrNull())
                }
            }
        }


        awaitClose { listenerRegistration.remove() }
    }


}
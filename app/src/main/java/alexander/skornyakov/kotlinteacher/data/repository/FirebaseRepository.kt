package alexander.skornyakov.kotlinteacher.data.repository

import alexander.skornyakov.kotlinteacher.data.model.SectionModel
import alexander.skornyakov.kotlinteacher.data.model.StepModel
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FirebaseRepository @Inject constructor(val context: Context): IRepository{

    @Inject lateinit var firestore: FirebaseFirestore

    override fun getAllSections(): Flow<SectionModel> {
        return callbackFlow {
                val listener = firestore.collection("sections").orderBy("order")
                    .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                        querySnapshot?.documents?.let{ list ->
                            for(doc in list){
                                val sectionName = doc.getString("header")
                                val id = doc.id
                                sectionName?.let {
                                    val sectionModel = SectionModel(id, sectionName)
                                    offer(sectionModel)
                                }
                            }
                        }
                    }
                awaitClose { listener.remove() }
        }
    }

    override fun getStepModelsBySection(sectionId: String): Flow<StepModel> {
        return callbackFlow {
            val listener = firestore.collection("sections")
                .document(sectionId)
                .collection("steps").orderBy("order")
                .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                    querySnapshot?.documents?.let {
                        CoroutineScope(Dispatchers.IO).launch{
                            for(doc in it){
                                val header = doc.getString("header")
                                header?.let {
                                    val text = doc.getString("text")
                                    val order = doc.getLong("order")?.toInt()
                                    val imgUrl = doc.getString("image")
                                    val bitmap =  imgUrl?.let{
                                        val gsReference = FirebaseStorage.getInstance().getReferenceFromUrl(it)
                                        GlideApp.with(context)
                                            .asBitmap()
                                            .load(gsReference)
                                            .submit()
                                            .get()
                                    }

                                    val step = StepModel(header,bitmap, text, order!!)
                                    offer(step)
                                }
                            }
                        }

                    }
                }

            awaitClose{ listener.remove() }
        }
    }

}
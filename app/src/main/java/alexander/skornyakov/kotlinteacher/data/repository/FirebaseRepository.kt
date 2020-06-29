package alexander.skornyakov.kotlinteacher.data.repository

import alexander.skornyakov.kotlinteacher.data.model.SectionModel
import alexander.skornyakov.kotlinteacher.data.model.StepModel
import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FirebaseRepository @Inject constructor(val context: Context): IRepository{

    @Inject lateinit var firestore: FirebaseFirestore

    override fun getAllSections(): Flow<SectionModel> {
        TODO("Not yet implemented")
    }

    override fun getAllStepModels(): Flow<StepModel> {
        TODO("Not yet implemented")
    }

}
package alexander.skornyakov.kotlinteacher.data.repository

import alexander.skornyakov.kotlinteacher.data.model.SectionModel
import alexander.skornyakov.kotlinteacher.data.model.StepModel
import android.content.Context
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FirebaseRepository @Inject constructor(val context: Context): IRepository{
    override fun getAllSections(): Flow<SectionModel> {
        TODO("Not yet implemented")
    }

    override fun getAllSecondModels(): Flow<StepModel> {
        TODO("Not yet implemented")
    }

}
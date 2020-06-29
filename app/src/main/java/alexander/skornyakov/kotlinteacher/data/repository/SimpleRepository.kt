package alexander.skornyakov.kotlinteacher.data.repository

import alexander.skornyakov.kotlinteacher.R
import alexander.skornyakov.kotlinteacher.data.model.SectionModel
import alexander.skornyakov.kotlinteacher.data.model.StepModel
import android.content.Context
import android.graphics.BitmapFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SimpleRepository @Inject constructor(val context: Context) : IRepository {

    override fun getAllSections(): Flow<SectionModel> {
        return flow {
            repeat (10) {
                val item = SectionModel("Header $it")
                emit(item)
            }
        }
    }

    override fun getAllStepModels(): Flow<StepModel> {
        return flow {
            repeat (10) {
                val image =
                    BitmapFactory.decodeResource(context?.resources, R.drawable.daisies_5091308_640)
                val item =
                    StepModel("Second Header $it", image,
                        context.resources.getString(R.string.text))
                emit(item)
            }
        }
    }
}
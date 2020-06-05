package alexander.skornyakov.kotlinteacher.data.repository

import alexander.skornyakov.kotlinteacher.R
import alexander.skornyakov.kotlinteacher.data.model.MainFirstModel
import alexander.skornyakov.kotlinteacher.data.model.MainSecondModel
import android.content.Context
import android.graphics.BitmapFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SimpleRepository @Inject constructor(val context: Context) : IRepository {

    override fun getAllFirstModels(): Flow<MainFirstModel> {
        return flow {
            repeat (10) {
                val image =
                    BitmapFactory.decodeResource(context?.resources, R.drawable.daisies_5091308_640)
                val item =
                    MainFirstModel("Header $it", image,
                        context.resources.getString(R.string.chapter_text))
                emit(item)
            }
        }
    }

    override fun getAllSecondModels(): Flow<MainSecondModel> {
        return flow {
            repeat (10) {
                val image =
                    BitmapFactory.decodeResource(context?.resources, R.drawable.daisies_5091308_640)
                val item =
                    MainSecondModel("Second Header $it", image,
                        context.resources.getString(R.string.text))
                emit(item)
            }
        }
    }
}
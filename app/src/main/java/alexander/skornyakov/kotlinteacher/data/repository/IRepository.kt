package alexander.skornyakov.kotlinteacher.data.repository

import alexander.skornyakov.kotlinteacher.data.model.MainFirstModel
import alexander.skornyakov.kotlinteacher.data.model.MainSecondModel
import kotlinx.coroutines.flow.Flow

interface IRepository {

    fun getAllFirstModels() : Flow<MainFirstModel>
    fun getAllSecondModels() : Flow<MainSecondModel>

}
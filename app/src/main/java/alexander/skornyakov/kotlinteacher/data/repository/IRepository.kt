package alexander.skornyakov.kotlinteacher.data.repository

import alexander.skornyakov.kotlinteacher.data.model.SectionModel
import alexander.skornyakov.kotlinteacher.data.model.StepModel
import kotlinx.coroutines.flow.Flow

interface IRepository {

    fun getAllSections() : Flow<SectionModel>
    fun getAllStepModels() : Flow<StepModel>

}
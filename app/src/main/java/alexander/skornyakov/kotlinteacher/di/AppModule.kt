package alexander.skornyakov.kotlinteacher.di

import alexander.skornyakov.kotlinteacher.ViewModelProviderFactory
import alexander.skornyakov.kotlinteacher.data.repository.IRepository
import alexander.skornyakov.kotlinteacher.data.repository.SimpleRepository
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule{
    @Binds
    abstract fun bindViewModelProviderFactory(factory: ViewModelProviderFactory):ViewModelProvider.Factory

    @Binds
    abstract fun bindSimpleRepository(repository: SimpleRepository): IRepository

}
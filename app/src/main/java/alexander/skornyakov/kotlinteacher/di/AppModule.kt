package alexander.skornyakov.kotlinteacher.di

import alexander.skornyakov.kotlinteacher.ViewModelProviderFactory
import alexander.skornyakov.kotlinteacher.data.repository.FirebaseRepository
import alexander.skornyakov.kotlinteacher.data.repository.IRepository
import alexander.skornyakov.kotlinteacher.data.repository.SimpleRepository
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class AppModule{
    @Binds
    abstract fun bindViewModelProviderFactory(factory: ViewModelProviderFactory):ViewModelProvider.Factory




}
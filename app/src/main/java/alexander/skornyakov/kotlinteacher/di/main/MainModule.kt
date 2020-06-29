package alexander.skornyakov.kotlinteacher.di.main

import alexander.skornyakov.kotlinteacher.data.repository.FirebaseRepository
import alexander.skornyakov.kotlinteacher.data.repository.IRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class MainModule {
    @Binds
    abstract fun bindFirebaseRepository(repository: FirebaseRepository): IRepository

    companion object {
        @Provides
        fun provideFirestore(): FirebaseFirestore = Firebase.firestore
    }

}
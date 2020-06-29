package alexander.skornyakov.kotlinteacher.di.main

import alexander.skornyakov.kotlinteacher.di.ViewModelKey
import alexander.skornyakov.kotlinteacher.ui.main.first.FirstViewModel
import alexander.skornyakov.kotlinteacher.ui.main.second.SecondViewModel
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(FirstViewModel::class)
    abstract fun bindFirstViewModel(vm: FirstViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SecondViewModel::class)
    abstract fun bindSecondViewModel(vm: SecondViewModel):ViewModel

    @Module
    companion object{
        @Provides
        @JvmStatic
        fun provideFirestore():FirebaseFirestore = Firebase.firestore
    }

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()
    }
}
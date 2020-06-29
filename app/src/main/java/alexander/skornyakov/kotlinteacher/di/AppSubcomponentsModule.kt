package alexander.skornyakov.kotlinteacher.di

import alexander.skornyakov.kotlinteacher.di.main.MainComponent
import alexander.skornyakov.kotlinteacher.di.start.StartComponent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [StartComponent::class, MainComponent::class])
class AppSubcomponentsModule{
    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()



    }
}
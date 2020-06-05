package alexander.skornyakov.kotlinteacher.di

import alexander.skornyakov.kotlinteacher.di.main.MainComponent
import alexander.skornyakov.kotlinteacher.di.start.StartComponent
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [StartComponent::class, MainComponent::class])
class AppSubcomponentsModule{
    @Module
    companion object {
        @JvmStatic
        @Provides
        @Singleton
        fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()
    }
}
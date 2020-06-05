package alexander.skornyakov.kotlinteacher.di

import alexander.skornyakov.kotlinteacher.di.main.MainComponent
import alexander.skornyakov.kotlinteacher.di.start.StartComponent
import alexander.skornyakov.kotlinteacher.ui.splash.SplashActivity
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, AppSubcomponentsModule::class])
interface AppComponent{

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context):AppComponent
    }

    fun startComponent():StartComponent.Factory
    fun mainComponent():MainComponent.Factory
    fun inject(activity: SplashActivity)
}
package alexander.skornyakov.kotlinteacher

import alexander.skornyakov.kotlinteacher.di.AppComponent
import alexander.skornyakov.kotlinteacher.di.DaggerAppComponent
import android.app.Application

class BaseApplication : Application(){

    val appComponent : AppComponent by lazy{
        DaggerAppComponent.factory().create(applicationContext)
    }

}
package alexander.skornyakov.kotlinteacher.ui.splash

import alexander.skornyakov.kotlinteacher.BaseApplication
import alexander.skornyakov.kotlinteacher.R
import alexander.skornyakov.kotlinteacher.data.ApplicationStorage
import alexander.skornyakov.kotlinteacher.ui.main.MainActivity
import alexander.skornyakov.kotlinteacher.ui.start.StartActivity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashActivity : AppCompatActivity(){

    @Inject
    lateinit var storage: ApplicationStorage

    val SPLASH_LATENCY = 300L

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BaseApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        // 1. Set layout
        setContentView(R.layout.splash_activity)

        // 2. Check settings and move forward
        GlobalScope.launch {
            Thread.sleep(SPLASH_LATENCY)
            if(checked()) {
                Intent(applicationContext, MainActivity::class.java).let {
                    startActivity(it)
                    finish()
                }
            }
            else{
                Intent(applicationContext, StartActivity::class.java).let {
                    startActivity(it)
                    finish()
                }
            }
        }
    }

    fun checked():Boolean{
        val firstLaunch = storage.getString("firstLaunch")
        if(firstLaunch=="false"){
            return true
        }
        else{
            storage.putString("firstLaunch","false")
            return false
        }
    }
}
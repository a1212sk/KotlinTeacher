package alexander.skornyakov.kotlinteacher.ui.start

import alexander.skornyakov.kotlinteacher.BaseApplication
import alexander.skornyakov.kotlinteacher.R
import alexander.skornyakov.kotlinteacher.di.start.StartComponent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class StartActivity : AppCompatActivity() {

    lateinit var startComponent: StartComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        startComponent = (application as BaseApplication).appComponent.startComponent().create()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_activity)
    }

}

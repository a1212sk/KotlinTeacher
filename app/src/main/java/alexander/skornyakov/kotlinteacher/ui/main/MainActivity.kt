package alexander.skornyakov.kotlinteacher.ui.main

import alexander.skornyakov.kotlinteacher.BaseApplication
import alexander.skornyakov.kotlinteacher.R
import alexander.skornyakov.kotlinteacher.di.main.MainComponent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mainComponent: MainComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        mainComponent = (application as BaseApplication).appComponent.mainComponent().create()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()
    }

    private fun setupToolbar(){
        var navController = findNavController(R.id.nav_controller)
        var appBarConfiguration = AppBarConfiguration(navController.graph, drawer)
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    fun setTitle(title: String){
        toolbar.title = title
    }
}

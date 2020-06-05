package alexander.skornyakov.kotlinteacher.ui.main

import alexander.skornyakov.kotlinteacher.BaseApplication
import alexander.skornyakov.kotlinteacher.R
import alexander.skornyakov.kotlinteacher.di.main.MainComponent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var mainComponent: MainComponent

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    lateinit var user: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        mainComponent = (application as BaseApplication).appComponent.mainComponent().create()
        mainComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()

        firebaseAuth.signInAnonymously()
            .addOnCompleteListener {
                Log.d("MainActivity",it.result?.user?.uid + " has logged in")
                user = it.result?.user!!
            }
            .addOnFailureListener {
                Log.e("MainActivity", it.message)
            }
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

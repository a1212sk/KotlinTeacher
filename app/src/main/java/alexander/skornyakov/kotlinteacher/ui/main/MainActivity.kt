package alexander.skornyakov.kotlinteacher.ui.main

import alexander.skornyakov.kotlinteacher.BaseApplication
import alexander.skornyakov.kotlinteacher.R
import alexander.skornyakov.kotlinteacher.data.repository.FirebaseRepository
import alexander.skornyakov.kotlinteacher.di.main.MainComponent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var mainComponent: MainComponent

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    @Inject
    lateinit var repository: FirebaseRepository

    lateinit var user: FirebaseUser

    lateinit var loadDataJob: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        mainComponent = (application as BaseApplication).appComponent.mainComponent().create()
        mainComponent.inject(this)
        super.onCreate(savedInstanceState)


        firebaseAuth.signInAnonymously()
            .addOnCompleteListener {
                Log.d("MainActivity",it.result?.user?.uid + " has logged in")
                user = it.result?.user!!
                setContentView(R.layout.activity_main)
                setupToolbar()
                initNavigationView()
            }
            .addOnFailureListener {
                Log.e("MainActivity", it.message)
            }

    }

    private fun initNavigationView(){
        navigation_view.menu.clear()
        loadDataJob = CoroutineScope(Dispatchers.IO + Job()).launch {
            repository.getAllSections()
                .flowOn(Dispatchers.IO)
                .collect {sectionModel->
                    withContext(Dispatchers.Main){
                        navigation_view.menu.add(sectionModel.header)
                            .setOnMenuItemClickListener {

                                val bundle = bundleOf("chapterId" to sectionModel.id,
                                    "header" to sectionModel.header)
                                findNavController(R.id.main_nav_controller).popBackStack()
                                findNavController(R.id.main_nav_controller).navigate(R.id.mainSecondFragment, bundle)
                                drawer.closeDrawers()
                                true
                            }
                    }
                }
        }
    }

    private fun setupToolbar(){
        var navController = findNavController(R.id.main_nav_controller)
        var appBarConfiguration = AppBarConfiguration(navController.graph, drawer)
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    fun setTitle(title: String){
        toolbar.title = title
    }

    override fun onDestroy() {
        super.onDestroy()
        loadDataJob.cancel()
    }
}

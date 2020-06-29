package alexander.skornyakov.kotlinteacher.di.main

import alexander.skornyakov.kotlinteacher.ui.main.MainActivity
import alexander.skornyakov.kotlinteacher.ui.main.first.FirstFragment
import alexander.skornyakov.kotlinteacher.ui.main.second.SecondFragment
import dagger.Subcomponent


@Subcomponent(modules = [MainViewModelsModule::class, MainModule::class])
interface MainComponent{

    @Subcomponent.Factory
    interface Factory{
        fun create(): MainComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: FirstFragment)
    fun inject(fragment: SecondFragment)

}



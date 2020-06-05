package alexander.skornyakov.kotlinteacher.di.start

import alexander.skornyakov.kotlinteacher.ui.start.StartActivity
import alexander.skornyakov.kotlinteacher.ui.start.first.FirstFragment
import alexander.skornyakov.kotlinteacher.ui.start.first.SecondFragment
import dagger.Subcomponent

@Subcomponent(modules = [StartModule::class])
interface StartComponent{

    @Subcomponent.Factory
    interface Factory{
        fun create():StartComponent
    }

    fun inject(activity: StartActivity)
    fun inject(fragment: FirstFragment)
    fun inject(fragment: SecondFragment)

}
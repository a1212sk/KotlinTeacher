package alexander.skornyakov.kotlinteacher.di.start

import alexander.skornyakov.kotlinteacher.di.ViewModelKey
import alexander.skornyakov.kotlinteacher.ui.start.first.FirstViewModel
import alexander.skornyakov.kotlinteacher.ui.start.first.SecondViewModel
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class StartModule {
    @Binds
    @IntoMap
    @ViewModelKey(FirstViewModel::class)
    abstract fun bindFirstViewModel(vm: FirstViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SecondViewModel::class)
    abstract fun bindSecondViewModel(vm: SecondViewModel): ViewModel
}
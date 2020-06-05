package alexander.skornyakov.kotlinteacher.di

import alexander.skornyakov.kotlinteacher.di.main.MainComponent
import alexander.skornyakov.kotlinteacher.di.start.StartComponent
import dagger.Module

@Module(subcomponents = [StartComponent::class, MainComponent::class])
abstract class AppSubcomponentsModule
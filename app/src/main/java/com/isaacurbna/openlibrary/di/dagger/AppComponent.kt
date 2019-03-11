package com.isaacurbna.openlibrary.di.dagger

import com.isaacurbna.openlibrary.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

}
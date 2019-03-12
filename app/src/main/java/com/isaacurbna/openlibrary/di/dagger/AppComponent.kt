package com.isaacurbna.openlibrary.di.dagger

import com.isaacurbna.openlibrary.OpenLibraryApplication
import com.isaacurbna.openlibrary.view.MainActivity
import com.isaacurbna.openlibrary.viewmodel.MainActivityViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: OpenLibraryApplication): Builder
    }

    fun inject(mainActivity: MainActivity)

    fun inject(mainActivityViewModel: MainActivityViewModel)

}
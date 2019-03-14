package com.isaacurbna.openlibrary

import android.app.Application
import com.isaacurbna.openlibrary.di.dagger.AppComponent
import com.isaacurbna.openlibrary.di.dagger.DaggerAppComponent

class OpenLibraryApplication : Application() {

    companion object {
        private const val TAG = "OpenLibraryApplication"
    }

    // dagger component
    val component: AppComponent by lazy {
        DaggerAppComponent.builder()
                .application(this)
                .build()
    }

}
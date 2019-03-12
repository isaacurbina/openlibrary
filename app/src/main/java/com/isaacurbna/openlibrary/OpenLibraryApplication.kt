package com.isaacurbna.openlibrary

import android.app.Application
import android.util.Log
import com.isaacurbna.openlibrary.di.dagger.AppComponent
import com.isaacurbna.openlibrary.di.dagger.AppModule
import com.isaacurbna.openlibrary.di.dagger.DaggerAppComponent

class OpenLibraryApplication : Application() {

    companion object {
        private const val TAG = "OpenLibraryApplication"
    }

    val component: AppComponent by lazy {
        DaggerAppComponent.builder()
                .application(this)
                .build()
    }

    // region lifecycle
    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "onCreate: ")
    }
    // endregion

}
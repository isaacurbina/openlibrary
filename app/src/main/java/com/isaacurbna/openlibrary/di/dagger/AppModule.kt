package com.isaacurbna.openlibrary.di.dagger

import android.app.Application
import com.isaacurbna.openlibrary.io.retrofit.WebInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: Application) {

    @Provides
    @Singleton
    fun provideApplicationContext() = app.applicationContext

    @Provides
    @Singleton
    fun provideWebInteractor() = WebInteractor()

}

package com.isaacurbna.openlibrary.di.dagger

import com.isaacurbna.openlibrary.io.retrofit.WebInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideWebInteractor() = WebInteractor()

}

package com.isaacurbna.openlibrary.io.retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    companion object {
        fun create(): OpenLibraryService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(OpenLibraryService.BASE_URL)
                    .build()
            return retrofit.create(OpenLibraryService::class.java)
        }
    }
}
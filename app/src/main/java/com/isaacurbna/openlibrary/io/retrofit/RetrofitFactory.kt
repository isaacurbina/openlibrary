package com.isaacurbna.openlibrary.io.retrofit

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    companion object {

        private const val TAG: String = "RetrofitFactory"

        fun create(): OpenLibraryService {
            Log.i(TAG, "create: ")
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getOkHttpClient())
                    .baseUrl(OpenLibraryService.BASE_URL)
                    .build()
            return retrofit.create(OpenLibraryService::class.java)
        }

        private fun getOkHttpClient(): OkHttpClient {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }
            val okHttpClient = OkHttpClient.Builder().apply {
                this.addInterceptor(httpLoggingInterceptor)
            }.build()
            return okHttpClient
        }
    }
}
package com.isaacurbna.openlibrary.io.retrofit;

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenLibraryService {

    companion object {
        val BASE_URL: String = "http://openlibrary.org";
    }

    @GET("/search.json")
    fun search(@Query("q") query: String): Observable<List<String>>

    @GET("/search.json")
    fun searchByAuthor(@Query("author") author: String): Observable<List<String>>

    @GET("/search.json")
    fun searchByTitle(@Query("title") title: String): Observable<List<String>>
}

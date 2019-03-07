package com.isaacurbna.openlibrary.io.retrofit;

import com.isaacurbna.openlibrary.model.APIResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenLibraryService {

    companion object {
        const val BASE_URL: String = "http://openlibrary.org"
    }

    @GET("/search.json")
    fun search(@Query("q") query: String): Observable<APIResponse>

    @GET("/search.json")
    fun searchByAuthor(@Query("author") author: String): Observable<APIResponse>

    @GET("/search.json")
    fun searchByTitle(@Query("title") title: String): Observable<APIResponse>
}

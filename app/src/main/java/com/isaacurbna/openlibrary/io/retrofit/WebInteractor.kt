package com.isaacurbna.openlibrary.io.retrofit

import com.isaacurbna.openlibrary.model.APIResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WebInteractor {

    companion object {
        val openLibraryService: OpenLibraryService = RetrofitFactory.create()
    }

    fun search(query: String): Observable<APIResponse>? {
        return openLibraryService.search(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}
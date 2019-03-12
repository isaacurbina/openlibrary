package com.isaacurbna.openlibrary.io.retrofit

import com.isaacurbna.openlibrary.model.APIResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class WebInteractor {

    val openLibraryService by lazy {
        RetrofitFactory.create()
    }
    var disposable: Disposable? = null

    fun search(query: String): Observable<APIResponse>? {
        return openLibraryService.search(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun searchByAuthor(author: String): Observable<APIResponse>? {
        return openLibraryService.searchByAuthor(author)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun searchByTitle(title: String): Observable<APIResponse>? {
        return openLibraryService.searchByTitle(title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
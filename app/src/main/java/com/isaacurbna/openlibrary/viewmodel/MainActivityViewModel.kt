package com.isaacurbna.openlibrary.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.isaacurbna.openlibrary.OpenLibraryApplication
import com.isaacurbna.openlibrary.io.retrofit.WebInteractor
import com.isaacurbna.openlibrary.model.Doc
import javax.inject.Inject

class MainActivityViewModel : AndroidViewModel {

    companion object {
        private const val TAG = "MainActivityViewModel"
    }

    // region dependencies
    @Inject
    lateinit var webInteractor: WebInteractor
    // endregion

    // region attributes
    private val docList: MutableLiveData<List<Doc?>> by lazy {
        MutableLiveData<List<Doc?>>().also {
            loadDocs("Harry Potter")
        }
    }
    // endregion

    constructor(application: Application) : super(application) {
        (getApplication<Application>() as OpenLibraryApplication)
                .component
                .inject(this)
    }

    // region getters
    fun getDocList(): LiveData<List<Doc?>> {
        Log.i(TAG, "getDocList: ")
        return docList
    }
    // endregion

    // region helper methods
    @SuppressLint("CheckResult")
    private fun loadDocs(query: String): List<Doc?>? {
        Log.i(TAG, "loadDocs: query: " + query)
        var list: List<Doc?>? = ArrayList()
        val observable = webInteractor.search(query)
        observable?.subscribe(
                { result -> docList.value = result.docs },
                { error -> Log.e(TAG, "error loading docs", error) }
        )
        return list
    }
    // endregion

}
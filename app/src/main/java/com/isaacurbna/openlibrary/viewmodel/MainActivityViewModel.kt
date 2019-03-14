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

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        private const val TAG = "MainActivityViewModel"
    }

    // region attributes
    lateinit var query: String
    // endregion

    // region dependencies
    @Inject
    lateinit var webInteractor: WebInteractor
    // endregion

    // region attributes
    private val docList: MutableLiveData<List<Doc?>> by lazy {
        MutableLiveData<List<Doc?>>().also {
            loadDocs(query)
        }
    }
    // endregion

    init {
        (getApplication<Application>() as OpenLibraryApplication)
                .component
                .inject(this)
    }

    // region getters
    fun getDocList(query: String): LiveData<List<Doc?>> {
        this.query = query
        loadDocs(query)
        return docList
    }
    // endregion

    // region helper methods
    @SuppressLint("CheckResult")
    private fun loadDocs(query: String): List<Doc?>? {
        val list: List<Doc?>? = ArrayList()
        val observable = webInteractor.search(query)
        observable?.subscribe(
                { result -> docList.value = result.docs },
                { error -> Log.e(TAG, "error loading docs", error) }
        )
        return list
    }
    // endregion

}
package com.isaacurbna.openlibrary.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.isaacurbna.openlibrary.OpenLibraryApplication
import com.isaacurbna.openlibrary.R
import com.isaacurbna.openlibrary.io.retrofit.WebInteractor
import com.isaacurbna.openlibrary.model.APIResponse
import com.isaacurbna.openlibrary.model.Doc
import com.isaacurbna.openlibrary.viewmodel.MainActivityViewModel
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    // region attributes & variables
    var disposable: Disposable? = null
    var docList: List<Doc?>? = null
    // endregion

    // region dependencies
    @Inject
    lateinit var webInteractor: WebInteractor
    // endregion

    // region lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "onCreate: ")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // inject dependencies
        (applicationContext as OpenLibraryApplication)
                .component
                .inject(this)

        // viewmodel
        val model = ViewModelProviders
                .of(this)
                .get(MainActivityViewModel::class.java)
        model.getDocList()
                .observe(this, Observer<List<Doc?>> {
                    updateUI(it)
                })
    }

    override fun onResume() {
        Log.i(TAG, "onResume: ")
        super.onResume()
        /*val observable = webInteractor.searchByTitle("Harry Potter")
        handleResponse(observable)*/
    }

    override fun onPause() {
        Log.i(TAG, "onPause: ")
        super.onPause()
        if (disposable != null && !disposable!!.isDisposed) {
            disposable!!.dispose()
        }
    }
    // endregion

    // region helper methods
    private fun updateUI(newDocList: List<Doc?>?) {
        Log.i(TAG, "updateUI: newDocList: " + newDocList)
        docList = newDocList
    }

    private fun handleResponse(observable: Observable<APIResponse>?) {
        Log.i(TAG, "handleResponse: ")

        if (observable != null) {
            disposable = observable.subscribe(
                    { result -> showResult(result) },
                    { error -> showError(error) }
            )
        } else {
            Log.w(TAG, "observable is null")
        }
    }

    private fun showResult(response: APIResponse) {
        Log.i(TAG, "showResult: ")
        Log.i(TAG, "showResult: response.numFound: " + response.numFound)
        Log.i(TAG, "showResult: response.start: " + response.start)
        Toast.makeText(this, "found " + response.numFound, Toast.LENGTH_LONG).show()
    }

    private fun showError(t: Throwable?) {
        Log.i(TAG, "showError: ")
        Log.e(TAG, "showError: received throwable -> ", t)
        Toast.makeText(this, t!!.localizedMessage, Toast.LENGTH_LONG).show()
    }
    // endregion

}

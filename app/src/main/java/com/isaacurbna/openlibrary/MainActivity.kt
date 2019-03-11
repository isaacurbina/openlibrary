package com.isaacurbna.openlibrary

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.isaacurbna.openlibrary.io.retrofit.WebInteractor
import com.isaacurbna.openlibrary.model.APIResponse
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    // region attributes & variables
    var disposable: Disposable? = null
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

        val component = (this.applicationContext as OpenLibraryApplication).getAppComponent()
        component.inject(this)
    }

    override fun onResume() {
        Log.i(TAG, "onResume: ")
        super.onResume()
        val observable = webInteractor.search("Isaac Asimov")
        handleResponse(observable)
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
        if (response.docs != null) {
            for (doc in response.docs!!) {
                Log.d(TAG, "doc: " + doc.toString())
            }
        }
    }

    private fun showError(t: Throwable?) {
        Log.i(TAG, "showError: ")
        Log.e(TAG, "showError: received throwable -> ", t)
        Toast.makeText(this, t!!.localizedMessage, Toast.LENGTH_LONG).show()
    }
    // endregion

}

package com.isaacurbna.openlibrary

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.isaacurbna.openlibrary.io.retrofit.WebInteractor
import com.isaacurbna.openlibrary.model.APIResponse
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG: String = "MainActivity"
    }

    // TODO: inject as dependencies
    lateinit var webInteractor: WebInteractor
    var disposable: Disposable? = null
    // TODO: end ^^^

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "onCreate: ")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO: remove this when dependencies are injected
        webInteractor = WebInteractor()
        // TODO: end ^^^
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
    }

    private fun showError(t: Throwable?) {
        Log.i(TAG, "showError: ")
        Log.e(TAG, "showError: received throwable -> ", t)
    }

}

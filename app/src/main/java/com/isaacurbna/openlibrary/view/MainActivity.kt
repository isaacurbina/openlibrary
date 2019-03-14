package com.isaacurbna.openlibrary.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.isaacurbna.openlibrary.OpenLibraryApplication
import com.isaacurbna.openlibrary.R
import com.isaacurbna.openlibrary.adapter.DocAdapter
import com.isaacurbna.openlibrary.io.retrofit.WebInteractor
import com.isaacurbna.openlibrary.model.Doc
import com.isaacurbna.openlibrary.viewmodel.MainActivityViewModel
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    // region attributes & variables
    var disposable: Disposable? = null
    var docList: List<Doc?>? = null
    lateinit var model: MainActivityViewModel
    // endregion

    // region dependencies
    @Inject
    lateinit var webInteractor: WebInteractor
    // endregion

    // region lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        injectDependencies()

        setupViewModel()

        setClickListeners()

        setupRecyclerView()
    }

    override fun onPause() {
        super.onPause()
        if (disposable != null && !disposable!!.isDisposed) {
            disposable!!.dispose()
        }
    }
    // endregion

    // region helper methods
    private fun injectDependencies() {
        // inject dependencies
        (applicationContext as OpenLibraryApplication)
                .component
                .inject(this)
    }

    private fun setupViewModel() {
        // viewmodel
        model = ViewModelProviders
                .of(this)
                .get(MainActivityViewModel::class.java)
    }

    private fun setClickListeners() {
        searchButton.setOnClickListener {
            // observer for viewmodel/livedata changes
            model.getDocList(queryEditText.text.toString())
                    .observe(this, Observer<List<Doc?>> {
                        updateUI(it)
                    })
        }
    }

    private fun updateUI(newDocList: List<Doc?>?) {
        docList = newDocList
        recyclerView.adapter = DocAdapter(docList, this)
        recyclerView.invalidate()
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = DocAdapter(docList, this)
    }
    // endregion

}

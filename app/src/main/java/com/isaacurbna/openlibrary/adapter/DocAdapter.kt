package com.isaacurbna.openlibrary.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.isaacurbna.openlibrary.R
import com.isaacurbna.openlibrary.model.Doc
import kotlinx.android.synthetic.main.doc_list_item.view.*

class DocAdapter(val docList: List<Doc?>?, val context: Context) : RecyclerView.Adapter<DocAdapter.DocViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocViewHolder {
        return DocViewHolder(LayoutInflater.from(context).inflate(R.layout.doc_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        if (docList == null) {
            return 0
        } else {
            return docList.size
        }
    }

    override fun onBindViewHolder(viewHolder: DocViewHolder, pos: Int) {
        viewHolder.titleTextView.text = docList?.get(pos)?.title
        viewHolder.authorTextView.text = docList?.get(pos)?.authorName?.get(0)
        viewHolder.firstPublishYear.text = docList?.get(pos)?.firstPublishYear?.toString()
        viewHolder.languageTextView.text = docList?.get(pos)?.language?.get(0)
        viewHolder.publisherTextView.text = docList?.get(pos)?.publisher?.get(0)
    }

    // view holder
    class DocViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView = view.titleTextView
        val authorTextView = view.authorTextView
        val firstPublishYear = view.firstPublishYear
        val languageTextView = view.languageTextView
        val publisherTextView = view.publisherTextView
    }

}
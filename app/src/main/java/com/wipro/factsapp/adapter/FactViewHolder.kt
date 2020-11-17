package com.wipro.factsapp.adapter

import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wipro.factsapp.R
import com.wipro.factsapp.model.Fact

/**
 * ViewHolder class for FactsList
 */
class FactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title: TextView = itemView.findViewById(R.id.fact_title)
    private val desc: TextView = itemView.findViewById(R.id.description)
    private val factImage: ImageView = itemView.findViewById(R.id.fact_image)

    /**
     * Binds the data into the views
     *
     * @param fact Fact data
     */
    fun setFactData(fact: Fact) {
        if (TextUtils.isEmpty(fact.title)) {
            title.visibility = View.GONE
        } else {
            title.visibility = View.VISIBLE
            title.text = fact.title
        }
        if (TextUtils.isEmpty(fact.description)) {
            desc.visibility = View.GONE
        } else {
            desc.visibility = View.VISIBLE
            desc.text = fact.description
        }
        if (fact.imageHref == null) {
            factImage.visibility = View.GONE
        } else {
            factImage.visibility = View.VISIBLE
        }
    }
}
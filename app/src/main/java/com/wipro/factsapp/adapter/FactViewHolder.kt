package com.wipro.factsapp.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wipro.factsapp.R

/**
 * ViewHolder class for FactsList
 */
class FactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val titleView: TextView = itemView.findViewById(R.id.fact_title)
    val descView: TextView = itemView.findViewById(R.id.description)
    val factImage: ImageView = itemView.findViewById(R.id.fact_image)
}
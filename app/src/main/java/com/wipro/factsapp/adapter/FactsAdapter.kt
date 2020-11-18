package com.wipro.factsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wipro.factsapp.FactsListPresenter
import com.wipro.factsapp.R
import com.wipro.factsapp.model.Fact

/**
 * Adapter for FactsList
 */
class FactsAdapter(private val presenter: FactsListPresenter) :
    RecyclerView.Adapter<FactViewHolder>() {

    private val factsList = ArrayList<Fact>()

    /**
     * Sets the facts list data and notifies adapter about data change
     *
     * @param factsList Facts list data
     */
    fun setFactsList(factsList: List<Fact>) {
        this.factsList.clear()
        this.factsList.addAll(factsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.fact_item_view, parent, false)
        return FactViewHolder(itemView)
    }

    override fun getItemCount() = factsList.size

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
        presenter.loadFactData(holder, factsList[position])
    }
}
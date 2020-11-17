package com.wipro.factsapp

import android.content.Context
import com.wipro.factsapp.adapter.FactViewHolder
import com.wipro.factsapp.model.Fact

/**
 * Presenter for FactsListActivity
 */
interface FactsListPresenter {

    /**
     * Checks for internet connection and loads data if internet connectivity is available.
     *
     * @param context Context
     */
    fun loadFacts(context: Context)

    /**
     * Binds the data into the views
     *
     * @param viewHolder View holder
     * @param fact Fact data
     */
    fun loadFactData(viewHolder: FactViewHolder, fact: Fact)
}
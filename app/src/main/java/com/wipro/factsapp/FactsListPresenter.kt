package com.wipro.factsapp

import android.content.Context
import android.os.Bundle
import com.wipro.factsapp.adapter.FactViewHolder
import com.wipro.factsapp.model.Fact

/**
 * Presenter for FactsListActivity
 */
interface FactsListPresenter {

    /**
     * Validates the savedInstanceState and restores the state
     *
     * @param savedInstanceState Bundle
     * @param context Context
     */
    fun restoreState(savedInstanceState: Bundle?, context: Context)

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

    /**
     * Saves the state when orientation changes
     */
    fun saveState(outState: Bundle)
}
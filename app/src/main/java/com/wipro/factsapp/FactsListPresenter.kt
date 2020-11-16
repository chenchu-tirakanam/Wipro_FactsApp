package com.wipro.factsapp

import android.content.Context

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
}
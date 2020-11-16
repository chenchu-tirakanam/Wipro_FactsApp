package com.wipro.factsapp

import androidx.annotation.StringRes

/**
 * View for FactsListActivity
 */
interface FactsListView {

    /**
     * Shows error message
     *
     * @param error String resource id representing the error
     */
    fun showError(@StringRes error: Int)
}
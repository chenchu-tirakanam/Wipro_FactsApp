package com.wipro.factsapp

import androidx.annotation.StringRes
import com.wipro.factsapp.model.Fact

/**
 * View for FactsListActivity
 */
interface FactsListView {

    /**
     * Shows loading for SwipeLayout
     */
    fun showLoading()

    /**
     * Shows error message
     *
     * @param error String resource id representing the error
     */
    fun showError(@StringRes error: Int)

    /**
     * Shows error message in Toast
     *
     * @param error String resource id representing the error
     */
    fun showErrorToast(@StringRes error: Int)

    /**
     * Sets Title in the actionbar
     *
     * @param title Title
     */
    fun setActionbarTitle(title: String)

    /**
     * Shows list in the RecyclerView
     *
     * @param factsList List data
     */
    fun showList(factsList: List<Fact>)
}
package com.wipro.factsapp

import android.content.Context
import com.wipro.factsapp.utils.NetworkUtils

/**
 * Implementation for the FactsListPresenter
 */
class FactsListPresenterImpl(private val view: FactsListView) : FactsListPresenter {

    override fun loadFacts(context: Context) {
        if (NetworkUtils.isNetworkConnected(context)) {

        } else {
            view.showError(R.string.error_no_internet)
        }
    }
}
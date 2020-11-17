package com.wipro.factsapp

import android.content.Context
import com.wipro.factsapp.api.ApiManager
import com.wipro.factsapp.model.Fact
import com.wipro.factsapp.model.FactsResponse
import com.wipro.factsapp.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Implementation for the FactsListPresenter
 */
class FactsListPresenterImpl(private val view: FactsListView) : FactsListPresenter {

    private var factsResponse: FactsResponse? = null

    override fun loadFacts(context: Context) {
        if (NetworkUtils.isNetworkConnected(context)) {
            loadFactsFromApi()
        } else {
            view.showError(R.string.error_no_internet)
        }
    }

    /**
     * Loads Facts from API
     */
    private fun loadFactsFromApi() {
        ApiManager.getFacts().enqueue(object : Callback<FactsResponse> {
            override fun onFailure(call: Call<FactsResponse>?, t: Throwable?) {
                view.showError(R.string.error_api)
            }

            override fun onResponse(call: Call<FactsResponse>?,
                response: Response<FactsResponse>?) {

                response?.run {
                    if (response.isSuccessful) {
                        onSuccess(response.body())
                    }
                } ?: view.showError(R.string.error_api)

            }
        })
    }

    /**
     * Success callback for API
     *
     * @param factsResponse API response
     */
    private fun onSuccess(factsResponse: FactsResponse) {
        this.factsResponse = factsResponse
        factsResponse.title?.run { view.setActionbarTitle(this) }
        showList(factsResponse.list)
    }

    /**
     * Shows list in the view if the data is not empty. Else will show error message
     *
     * @param factsList List data
     */
    private fun showList(factsList: List<Fact>?) {
        if (factsList != null && factsList.isNotEmpty()) {
            view.showList(factsList)
        } else {
            view.showError(R.string.error_empty_list)
        }
    }
}
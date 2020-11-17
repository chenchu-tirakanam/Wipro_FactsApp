package com.wipro.factsapp

import android.content.Context
import androidx.annotation.StringRes
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

    private var title: String? = null
    private var list: List<Fact>? = null

    override fun loadFacts(context: Context) {
        if (NetworkUtils.isNetworkConnected(context)) {
            loadFactsFromApi()
        } else {
            showError(R.string.error_no_internet)
        }
    }

    private fun showError(@StringRes error: Int) {
        if (list == null || list!!.isEmpty()) {
            view.showError(error)
        } else {
            view.showErrorToast(error)
        }
    }

    /**
     * Loads Facts from API
     */
    private fun loadFactsFromApi() {
        ApiManager.getFacts().enqueue(object : Callback<FactsResponse> {
            override fun onFailure(call: Call<FactsResponse>?, t: Throwable?) {
                showError(R.string.error_api)
            }

            override fun onResponse(call: Call<FactsResponse>?,
                response: Response<FactsResponse>?) {

                response?.run {
                    if (response.isSuccessful) {
                        onSuccess(response.body())
                    }
                } ?: showError(R.string.error_api)

            }
        })
    }

    /**
     * Success callback for API
     *
     * @param factsResponse API response
     */
    private fun onSuccess(factsResponse: FactsResponse) {
        factsResponse.title?.run { setTitle(this) }
        if (factsResponse.list != null) {
            val list = factsResponse.list.filter { it.isValid() }
            showList(list)
        } else {
            showError(R.string.error_empty_list)
        }
    }

    private fun setTitle(title: String?) {
        if (title != null) {
            this.title = title
            view.setActionbarTitle(title)
        }
    }

    /**
     * Shows list in the view if the data is not empty. Else will show error message
     *
     * @param factsList List data
     */
    private fun showList(factsList: List<Fact>) {
        if (factsList.isNotEmpty()) {
            list = factsList
            view.showList(factsList)
        } else {
            showError(R.string.error_empty_list)
        }
    }
}
package com.wipro.factsapp.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

/**
 * Model class for API response
 */
class FactsResponse {

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("rows")
    @Expose
    var list: List<Fact>? = null
}
package com.wipro.factsapp.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

/**
 * Model class for API response
 */
class FactsResponse {

    @SerializedName("title")
    @Expose
    val title: String? = null

    @SerializedName("rows")
    @Expose
    val list: List<Fact>? = null
}
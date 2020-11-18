package com.wipro.factsapp.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

/**
 * Model class for Fact data
 */
class Fact {
    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("imageHref")
    @Expose
    var imageHref: String? = null

    fun isValid() = title != null || description != null
}
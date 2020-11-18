package com.wipro.factsapp.api

import com.wipro.factsapp.model.FactsResponse
import retrofit2.Call
import retrofit2.http.GET

interface FactsApi {

    @GET("2iodh4vg0eortkl/facts.json")
    fun getFacts(): Call<FactsResponse>
}
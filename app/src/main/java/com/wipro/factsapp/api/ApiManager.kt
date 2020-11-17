package com.wipro.factsapp.api

import com.google.gson.GsonBuilder
import com.wipro.factsapp.model.FactsResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Manager class for APIs
 */
object ApiManager {

    private const val BASE_URL = "https://dl.dropboxusercontent.com/s/"

    /**
     * Returns FactsApi created using retrofit
     *
     * @return FactsApi
     */
    private fun getFactsApi(): FactsApi {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(FactsApi::class.java)
    }

    /**
     * Returns getFactsApi() Retrofit call
     *
     * @return getFactsApi() call
     */
    fun getFacts(): Call<FactsResponse> {
        return getFactsApi().getFacts()
    }
}
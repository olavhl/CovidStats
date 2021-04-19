package no.olav.codelab.datasources.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface CovidApi {

    @GET("summary")
    fun getSummary(): Call<SummaryResponse>
}
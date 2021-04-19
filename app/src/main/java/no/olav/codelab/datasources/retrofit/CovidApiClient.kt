package no.olav.codelab.datasources.retrofit

import com.google.gson.GsonBuilder
import no.olav.codelab.models.CountryStats
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class CovidApiClient {

    private var covidApi: CovidApi
    init {
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://api.covid19api.com/")
            .build()

        covidApi = retrofit.create(CovidApi::class.java)
    }

    fun getSummary(): List<CountryStats> {

        try {
            val response = covidApi.getSummary().execute()

            if (response.isSuccessful) {
                response.body()?.Countries?.let { countries ->
                    return countries
                }
            }

        } catch (ex: Exception){
            ex.printStackTrace()
        }


        return ArrayList()

    }
}
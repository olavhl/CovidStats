package no.olav.codelab.repos

import no.olav.codelab.datasources.LiveSource
import no.olav.codelab.datasources.retrofit.CovidApiClient
import no.olav.codelab.models.CountryStats

class CovidRepo {

    private val covidApiClient = CovidApiClient()

    private val liveSource = LiveSource()

    suspend fun getCountriesSummary() : List<CountryStats> {
        var list = covidApiClient.getSummary()

        return list.sortedByDescending { it.TotalConfirmed }
    }

}
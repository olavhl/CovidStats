package no.olav.codelab.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import no.olav.codelab.models.CountryStats
import no.olav.codelab.repos.CovidRepo

class MainViewModel : ViewModel() {
    val liveStats = MutableLiveData<List<CountryStats>> (ArrayList<CountryStats>())
    var isLoading = MutableLiveData<Boolean> (false)

    private var repo = CovidRepo()

    fun refresh() {

        isLoading.value = true

        viewModelScope.launch {
            var result = withContext(Dispatchers.IO) {
                repo.getCountriesSummary()
            }

            isLoading.value = false
            liveStats.value = result
        }
    }

}
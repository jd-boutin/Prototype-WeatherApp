package com.example.weatherappprototype.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherappprototype.model.LocationRepository
import com.example.weatherappprototype.model.LocationWrapper
import com.example.weatherappprototype.model.Meteo
import com.example.weatherappprototype.model.MeteoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OverviewViewModel(private val application: Application): AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext

    val meteoRepository: MeteoRepository = MeteoRepository()
    val locationRepository: LocationRepository = LocationRepository()

    private val _searchBarActive = MutableStateFlow(false)
    val searchBarActive = _searchBarActive.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _searchResults = MutableLiveData<List<Meteo>>()
    val searchResults: LiveData<List<Meteo>> = _searchResults

    private val _overview = MutableLiveData<List<Meteo>>()
    val overview: LiveData<List<Meteo>> = _overview

    fun onSearchTextChange(text: String) {
        _searchText.value = text
        getSearchResults()
    }

    fun onSearchActivity(b: Boolean) {
        _searchBarActive.value = !_searchBarActive.value
        if (!_searchBarActive.value) {
            onSearchTextChange("")
        }
    }


    fun getMeteoListOverview() {
        viewModelScope.launch {
            val ov = MutableList<Meteo>(0, { index -> Meteo(LocationWrapper(2988507,"Paris", "France", 48.85341F, 2.3488F), 16.5, 1, false)})
            val pinnedLocations = locationRepository.getSavedLocations(context)
            for (location in pinnedLocations) {
                ov.add(meteoRepository.fetchMeteoData(location, context))
            }

            _overview.postValue(ov)
        }
    }


    fun onFavoriteClick(location: LocationWrapper) {
        if (locationRepository.findLocation(location, context) == -1){
            locationRepository.pinLocation(location, context)
        }
        else {
            locationRepository.unpinLocation(location, context)
        }

        getMeteoListOverview()
        getSearchResults()
    }

    fun getSearchResults(){
        viewModelScope.launch {
            val sr = MutableList<Meteo>(0, { index -> Meteo(LocationWrapper(2988507, "Paris", "France", 48.85341F, 2.3488F), 16.5, 1, false)})
            val results = locationRepository.fetchLocationSuggestions(_searchText.value)
            for (result in results) {
                sr.add(meteoRepository.fetchMeteoData(result, context))
            }
            _searchResults.postValue(sr)
        }
    }
}

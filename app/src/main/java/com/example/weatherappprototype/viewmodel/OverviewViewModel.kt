package com.example.weatherappprototype.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappprototype.model.LocationRepository
import com.example.weatherappprototype.model.Meteo
import com.example.weatherappprototype.model.MeteoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OverviewViewModel: ViewModel() {
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
            val ov = MutableList<Meteo>(0, { index -> Meteo("Paris", 16.5, 1)})
            val pinnedLocations = locationRepository.getSavedLocations()
            for (location in pinnedLocations) {
                ov.add(meteoRepository.fetchMeteoData(location))
            }

            _overview.postValue(ov)
        }
    }
    fun getSearchResults(){
        viewModelScope.launch {
            val sr = MutableList<Meteo>(0, { index -> Meteo("Paris", 16.5, 1)})
            val results = locationRepository.fetchLocationSuggestions(_searchText.value)
            for (result in results) {
                sr.add(meteoRepository.fetchMeteoData(locationRepository.locationWrapperToLocation(result)))
            }
            _searchResults.postValue(sr)
        }
    }
}
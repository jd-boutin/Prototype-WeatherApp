package com.example.weatherappprototype.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappprototype.model.LocationRepository
import com.example.weatherappprototype.model.Meteo
import com.example.weatherappprototype.model.MeteoRepository
import kotlinx.coroutines.launch

class OverviewViewModel: ViewModel() {
    val meteoRepository: MeteoRepository = MeteoRepository()
    val locationRepository: LocationRepository = LocationRepository()

    private val _overview = MutableLiveData<List<Meteo>>()
    val overview: LiveData<List<Meteo>> = _overview

    fun getMeteoListOverview() {
        viewModelScope.launch {
            val ov = MutableList<Meteo>(0, { index -> Meteo("Paris", 16.5F, 1)})
            val pinnedLocations = locationRepository.fetchLocations()
            for (location in pinnedLocations) {
                ov.add(meteoRepository.fetchMeteoData(location))
            }

            _overview.postValue(ov)
        }
    }
}
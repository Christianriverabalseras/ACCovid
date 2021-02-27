package com.ac03.covid.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ac03.covid.data.server.CovidServiceFactory
import com.ac03.covid.data.server.SummaryData
import com.ac03.covid.entites.Countries
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            return _model
        }

    init {
        viewModelScope.launch {
            _model.value = UiModel.ContentCountries(CovidServiceFactory.service.getCountries())
            _model.value = UiModel.ContentGlobalData(CovidServiceFactory.service.getSummary())
        }
    }

    sealed class UiModel {
        object Loading : UiModel()
        data class ContentCountries(val countries: Countries) : UiModel()
        data class ContentGlobalData(val data: SummaryData) : UiModel()
        object Error : UiModel()
    }
}
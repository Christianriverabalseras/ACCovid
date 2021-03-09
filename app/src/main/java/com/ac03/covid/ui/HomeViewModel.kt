package com.ac03.covid.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ac03.covid.data.server.CovidServiceFactory
import com.ac03.covid.data.server.SummaryData
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            return _model
        }

    init {
        viewModelScope.launch {
            try {
                _model.value = UiModel.Content(CovidServiceFactory.service.getSummary())
            } catch (e: Exception) {
                _model.value = UiModel.Error(e.message.orEmpty())
            }
        }
    }

    sealed class UiModel {
        object Loading : UiModel()
        data class Content(val data: SummaryData) : UiModel()
        data class Error(val message: String) : UiModel()
    }
}
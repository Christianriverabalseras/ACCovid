package com.ac03.covid.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ac03.covid.data.repository.CovidRepository
import com.ac03.covid.domain.Country
import com.ac03.covid.domain.SummaryData
import com.ac03.covid.usecases.GetSummaryData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(private val usecase: GetSummaryData) : ViewModel() {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            return _model
        }

    init {
        viewModelScope.launch {
            try {
                // TODO: implementar findCountries() en Covid Repository
                _model.value = UiModel.Content(usecase.invoke())
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

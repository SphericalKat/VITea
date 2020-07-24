package com.example.vitea.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vitea.models.ApiResult
import com.example.vitea.models.timetable.TimeTableResponse
import com.example.vitea.repository.WebRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val webRepo: WebRepo): ViewModel() {
    val timeTable = MutableLiveData<ApiResult<TimeTableResponse>>(ApiResult.loading())

    fun getTimeTable(regNo: String) = viewModelScope.launch(Dispatchers.IO) {
        val result = webRepo.getTimeTableAsync(regNo)
        timeTable.postValue(result)
    }
}
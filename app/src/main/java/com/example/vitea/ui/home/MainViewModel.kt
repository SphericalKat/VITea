package com.example.vitea.ui.home

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vitea.models.ApiResult
import com.example.vitea.models.timetable.TimeTableResponse
import com.example.vitea.repository.WebRepo
import com.example.vitea.utils.PreferenceHelper.get
import com.example.vitea.utils.PreferenceHelper.set
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val webRepo: WebRepo,
    private val prefs: SharedPreferences
) : ViewModel() {
    val timeTable = MutableLiveData<ApiResult<TimeTableResponse>>(ApiResult.loading())
    var doOnce = true

    fun getTimeTable(regNo: String) = viewModelScope.launch(Dispatchers.IO) {
        val gson = Gson()
        if (prefs["saved_timetable", ""] != "") {
            val saved = gson.fromJson(prefs["saved_timetable", ""], TimeTableResponse::class.java)
            timeTable.postValue(ApiResult.success(saved))
        }
        val result = webRepo.getTimeTableAsync(regNo)
        result.data?.let {
            prefs["saved_timetable"] = gson.toJson(result.data)
        }
        timeTable.postValue(result)
    }
}
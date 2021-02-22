package com.example.vitea.ui.home

import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vitea.models.ApiResult
import com.example.vitea.models.da.DAResponse
import com.example.vitea.models.profile.ProfileResponse
import com.example.vitea.models.timetable.TimeTableResponse
import com.example.vitea.repository.WebRepo
import com.example.vitea.utils.PreferenceHelper.get
import com.example.vitea.utils.PreferenceHelper.set
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val webRepo: WebRepo,
    private val prefs: SharedPreferences
) : ViewModel() {
    private val _timeTable = MutableLiveData<ApiResult<TimeTableResponse>>(ApiResult.loading())
    private val _profile = MutableLiveData<ApiResult<ProfileResponse>>(ApiResult.loading())
    var da: ApiResult<DAResponse> by mutableStateOf(ApiResult.loading())
        private set

    var doOnce = true
    private val gson = Gson()

    val timeTable: LiveData<ApiResult<TimeTableResponse>>
        get() = _timeTable
    val profile: LiveData<ApiResult<ProfileResponse>>
        get() = _profile

    fun getTimeTable(regNo: String) = viewModelScope.launch(Dispatchers.IO) {
        if (prefs["saved_timetable", ""] != "") {
            val saved = gson.fromJson(prefs["saved_timetable", ""], TimeTableResponse::class.java)
            _timeTable.postValue(ApiResult.success(saved))
        }
        val result = webRepo.getTimeTableAsync(regNo)
        result.data?.let {
            prefs["saved_timetable"] = gson.toJson(result.data)
        }
        _timeTable.postValue(result)
    }

    fun getProfile(regNo: String) = viewModelScope.launch(Dispatchers.IO) {
        if (prefs["saved_profile", ""] != "") {
            val saved = gson.fromJson(prefs["saved_profile", ""], ProfileResponse::class.java)
            _profile.postValue(ApiResult.success(saved))
        }
        val result = webRepo.getProfileAsync(regNo)
        result.data?.let {
            prefs["saved_profile"] = gson.toJson(result.data)
        }
        _profile.postValue(result)
    }

    fun getDa(regNo: String) = viewModelScope.launch(Dispatchers.IO) {
        if (prefs["saved_profile", ""] != "") {
            val saved = gson.fromJson(prefs["saved_da", ""], DAResponse::class.java)
            da = ApiResult.success(saved)
        }
        val result = webRepo.getDaAsync(regNo)
        result.data?.let {
            prefs["saved_da"] = gson.toJson(result.data)
        }
        da = result
    }
}
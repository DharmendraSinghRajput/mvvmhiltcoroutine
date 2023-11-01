package com.example.mvvmhiltcoroutine.ui

import android.app.sportapp.remote.Resource
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmhiltcoroutine.R
import com.example.mvvmhiltcoroutine.remote.RemoteService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel  @Inject constructor(private val workService: RemoteService) : ViewModel() {

    private var _getAllEducationDataByiUserIdAPIResponse = MutableLiveData<Resource<Any>>()
    var getAllEducationDataByiUserIdAPIResponse: LiveData<Resource<Any>> =
        _getAllEducationDataByiUserIdAPIResponse

    fun callGetAllEducationDataByiUserAPI(context: Context, params: HashMap<String, Any>) =
        viewModelScope.launch {
            _getAllEducationDataByiUserIdAPIResponse.value = Resource.Loading()
            try {
                val response = workService.getAllPostData(params)
                if (response.isSuccessful)
                    _getAllEducationDataByiUserIdAPIResponse.value =
                        Resource.Success(response.body()!!)
                else
                    _getAllEducationDataByiUserIdAPIResponse.value =
                        Resource.Error(response.message())
            } catch (e: Exception) {
                e.printStackTrace()
                _getAllEducationDataByiUserIdAPIResponse.value =
                    Resource.Error(e.message ?: context.getString(R.string.unexpected_error))
            }
        }

/*    private var _updateProfileAPIResponse = MutableLiveData<Resource<Any>>()
    var updateProfileAPIResponse: LiveData<Resource<Any>> = _updateProfileAPIResponse
    fun callUpdateProfileAPI(
        context: Context,
        params: HashMap<String, RequestBody>,
        profileImage: MultipartBody.Part,
        bannerImage: MultipartBody.Part
    ) = viewModelScope.launch {
        _updateProfileAPIResponse.value = Resource.Loading()

        try {
            val response = workService.updateProfile(params, profileImage, bannerImage)
            if (response.isSuccessful)
                _updateProfileAPIResponse.value = Resource.Success(response.body()!!)
            else
                _updateProfileAPIResponse.value = Resource.Error(response.message())
        } catch (e: Exception) {
            e.printStackTrace()
            _updateProfileAPIResponse.value =
                Resource.Error(e.message ?: context.getString(R.string.unexpected_error))
        }
    }*/

}
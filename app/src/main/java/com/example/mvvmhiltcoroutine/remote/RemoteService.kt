
package com.example.mvvmhiltcoroutine.remote

import com.example.mvvmhiltcoroutine.ui.GetAllPostResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface RemoteService {

/*    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(@FieldMap params: HashMap<String, Any>): Response<LoginResponse>*/

    @FormUrlEncoded
    @POST("post/getAllPostData")
    suspend fun getAllPostData(@FieldMap params: HashMap<String, Any>): Response<GetAllPostResponse>

  /*  @Multipart
    @POST("profile/profileUpdate")
    suspend fun updateProfile(
        @PartMap params: HashMap<String, RequestBody>, @Part profileImage: MultipartBody.Part, @Part bannerImage: MultipartBody.Part
    ): Response<UpdateProfileResponse>*/
}
package com.devfinity.composeboilerplate.remote

import com.devfinity.composeboilerplate.features.auth.data.model.LoginResponse
import com.devfinity.composeboilerplate.remote.helper.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface ApiService {
   @POST("/login")
   suspend fun login(@Body requestParams: Map<String, String>): BaseResponse<LoginResponse>
}
package com.example.getapi

import retrofit2.Retrofit
import retrofit2.http.GET

interface ApiInterface {
    @GET("/api/users")
    fun getData():retrofit2.Call<ResponseModel>
}

package com.example.weatherapp



import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call
import retrofit2.http.*


interface APIService{
    // ...

    @GET("weather?&lang=en&appid=49162d983649b7cd5e7eda3473bc246d&units=metric")
    suspend fun getEmployees(@Query("q") aParam: String): Response<ResponseBody>//Response<ResponseBody>

    // ...
}


package com.example.apiinandroid.Api

import com.example.apiinandroid.models.DataModelClassItem
import retrofit2.Call
import retrofit2.http.GET

//Created Api Interface class
interface ApiServices {
    @GET("posts")
    fun getRequest(): Call<List<DataModelClassItem>>
}
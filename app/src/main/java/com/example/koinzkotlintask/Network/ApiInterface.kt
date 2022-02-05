package com.example.cproductandroid.Network

import com.example.koinzkotlintask.model.KoinzModel
import okhttp3.MultipartBody
import retrofit2.Call
import okhttp3.RequestBody
import retrofit2.http.*


interface ApiInterface {



    @GET(Urls.GET_PRODUCTS)
    fun getPhotos(): Call<KoinzModel>

//    @GET(Urls.GET_PRODUCTS)
//    fun getPhotos2(@Body photoRequest: photoRequest): Call<KoinzModel>


}
package com.hfad.api

import com.hfad.model.Datas
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {

    @GET("/tweets")
    fun getTweets():Call<Datas>
}
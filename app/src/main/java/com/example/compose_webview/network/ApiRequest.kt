package com.example.compose_webview.network

import com.example.compose_webview.model.CharacterDataModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object ApiRequest{
    fun callGetCharacters(): Response<List<CharacterDataModel>> {


        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder().client(client)
            .baseUrl("https://hp-api.herokuapp.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        val retrofitAPI = retrofit.create(ApiInterface::class.java)

       return retrofitAPI.getCharacters()
    }
}
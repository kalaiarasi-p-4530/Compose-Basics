package com.example.compose_webview.network

import com.example.compose_webview.model.CharacterDataModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object ApiRequest{

    fun getRetrofit(): ApiInterface {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor).build()
         val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory())
            .build()
        val retrofit = Retrofit.Builder().client(client)
            .baseUrl("https://hp-api.herokuapp.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        return retrofit.create(ApiInterface::class.java)
    }
}
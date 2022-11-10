package com.example.compose_webview.network

import com.example.compose_webview.model.CharacterDataModel
import com.example.compose_webview.model.Resource
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("api/characters")
    fun getCharacters() : Response<List<CharacterDataModel>>
}
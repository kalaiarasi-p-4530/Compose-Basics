package com.example.compose_webview.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterDataModel(
    @Json(name = "actor")
    val actor: String = "",
    @Json(name = "house")
    val house: String = "",
    @Json(name = "image")
    val image: String = "",
    @Json(name = "name")
    val name: String = ""
)

/*
@JsonClass(generateAdapter = true)
data class CharacterDataModel(
    @Json(name = "actor")
    val actor: String = "",
    @Json(name = "alive")
    val alive: Boolean = false,
    @Json(name = "alternate_actors")
    val alternateActors: List<Any> = listOf(),
    @Json(name = "alternate_names")
    val alternateNames: List<Any> = listOf(),
    @Json(name = "ancestry")
    val ancestry: String = "",
    @Json(name = "dateOfBirth")
    val dateOfBirth: String = "",
    @Json(name = "eyeColour")
    val eyeColour: String = "",
    @Json(name = "gender")
    val gender: String = "",
    @Json(name = "hairColour")
    val hairColour: String = "",
    @Json(name = "hogwartsStaff")
    val hogwartsStaff: Boolean = false,
    @Json(name = "hogwartsStudent")
    val hogwartsStudent: Boolean = false,
    @Json(name = "house")
    val house: String = "",
    @Json(name = "image")
    val image: String = "",
    @Json(name = "name")
    val name: String = "",
    @Json(name = "patronus")
    val patronus: String = "",
    @Json(name = "species")
    val species: String = "",
    @Json(name = "wand")
    val wand: Wand = Wand(),
    @Json(name = "wizard")
    val wizard: Boolean = false,
    @Json(name = "yearOfBirth")
    val yearOfBirth: Int = 0
) {
    @JsonClass(generateAdapter = true)
    data class Wand(
        @Json(name = "core")
        val core: String = "",
        @Json(name = "length")
        val length: Int = 0,
        @Json(name = "wood")
        val wood: String = ""
    )
}*/

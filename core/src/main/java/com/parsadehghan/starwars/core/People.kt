package com.parsadehghan.starwars.core

import com.google.gson.annotations.SerializedName

data class StarWarsCharactersResponse(
    @SerializedName("count") val totalCharacters: Int,
    @SerializedName("next") val nextPageUrl: String?,
    @SerializedName("previous") val previousPageUrl: String?,
    @SerializedName("results") val characters: List<Character>
)

open class Character(
    @SerializedName("name") val characterName: String,
    @SerializedName("height") val characterHeight: String,
    @SerializedName("mass") val characterMass: String,
    @SerializedName("hair_color") val hairColor: String,
    @SerializedName("skin_color") val skinColor: String,
    @SerializedName("eye_color") val eyeColor: String,
    @SerializedName("birth_year") val birthYear: String,
    @SerializedName("gender") val characterGender: String,
    @SerializedName("homeworld") val homeWorldUrl: String,
    @SerializedName("films") val filmUrls: List<String>,
    @SerializedName("species") val speciesUrls: List<String>,
    @SerializedName("vehicles") val vehicleUrls: List<String>,
    @SerializedName("starships") val starshipUrls: List<String>,
    @SerializedName("created") val creationDate: String,
    @SerializedName("edited") val lastEditedDate: String,
    @SerializedName("url") val characterUrl: String
)
data class CharacterDetail(
    val name: String,
    val height: String,
    val mass: String,
    @SerializedName("hair_color")
    val hairColor: String,
    @SerializedName("skin_color")
    val skinColor: String,
    @SerializedName("eye_color")
    val eyeColor: String,
    @SerializedName("birth_year")
    val birthYear: String,
    val gender: String,
    val homeworld: String,
    val films: List<String>,
    val species: List<String>,
    val vehicles: List<String>,
    val starships: List<String>
)


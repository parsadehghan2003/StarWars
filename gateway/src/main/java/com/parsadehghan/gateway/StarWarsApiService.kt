package com.parsadehghan.gateway

import com.parsadehghan.starwars.core.CharacterDetail
import com.parsadehghan.starwars.core.StarWarsCharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface
StarWarsApiService {

    @GET("people/")
    suspend fun getPeoples(@Query("page") page: Int?,@Query("search") search: String?): Response<StarWarsCharactersResponse>

    @GET("people/{id}/")
    suspend fun getPeople(@Path("id") id: Int?): Response<CharacterDetail>
//
//    @GET("carts")
//    suspend fun initCart(): Response<InitCartResponse>

}
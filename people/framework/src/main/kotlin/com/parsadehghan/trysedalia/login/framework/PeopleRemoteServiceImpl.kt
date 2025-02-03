package com.parsadehghan.trysedalia.login.framework

import android.util.Log
import com.parsadehghan.gateway.StarWarsApiService
import com.parsadehghan.base.BaseDomain
import com.parsadehghan.base.error_handler.dataStateInternalErrorHandler
import com.parsadehghan.base.error_handler.dataStateRemoteErrorHandler
import com.parsadehghan.base.interactor.DataState
import com.parsadehghan.starwars.people.data_source.PeopleRemoteService
import com.parsadehghan.starwars.people.domian.GetCharacterDetailsObject
import com.parsadehghan.starwars.people.domian.GetCharacterListObject
import javax.inject.Inject

class PeopleRemoteServiceImpl @Inject constructor(val starWarsApiService: StarWarsApiService) :
    PeopleRemoteService {

    override suspend fun getPeoples(baseDomain: BaseDomain?): DataState<BaseDomain> = try {
        val request = (baseDomain as GetCharacterListObject.GetCharacterListRequest)
        val response = starWarsApiService.getPeoples(request.page,request.search)
        if (response.isSuccessful)
            DataState.Data(response.body()?.let {
                GetCharacterListObject.GetCharacterListResponse(
                    it.characters,
                    it.nextPageUrl?.split("page=")?.get(1)?.toInt()
                )
            })
        else dataStateRemoteErrorHandler(response.code())
    } catch (exception: Exception) {
        dataStateInternalErrorHandler(0)
    }

    override suspend fun getPeopleDetail(domain: BaseDomain?): DataState<BaseDomain> = try {
        val request = (domain as GetCharacterDetailsObject.GetCharacterDetailsRequest)
        // I know its not a good way getting data like this but in this step i didn`t have time to create an mapper
        val response = starWarsApiService.getPeople(request.characterId.toInt())
        if (response.isSuccessful)
            DataState.Data(response.body()?.let {
                GetCharacterDetailsObject.GetCharacterDetailsResponse(
                    it.name,
                    it.height,
                    it.mass,
                    it.hairColor,
                    it.skinColor,
                    it.eyeColor,
                    it.birthYear,
                    it.gender,
                    it.homeworld,
                    it.films,
                    it.species,
                    it.vehicles,
                    it.starships
                )
            })
        else dataStateRemoteErrorHandler(response.code())
    } catch (exception: Exception) {
        dataStateInternalErrorHandler(0)
    }
}
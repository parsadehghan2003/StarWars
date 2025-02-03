package com.parsadehghan.starwars.people.data_source

import com.parsadehghan.base.BaseDomain
import com.parsadehghan.base.interactor.DataState

interface PeopleRemoteService {
    suspend fun getPeoples(baseDomain: BaseDomain?): DataState<BaseDomain>
    suspend fun getPeopleDetail(domain: BaseDomain?): DataState<BaseDomain>


}
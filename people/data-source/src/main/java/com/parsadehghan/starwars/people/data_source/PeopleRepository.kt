package com.parsadehghan.starwars.people.data_source

import com.parsadehghan.base.interactor.DataState
import com.parsadehghan.base.BaseDomain

interface PeopleRepository {
    suspend fun getPeoples(baseDomain: BaseDomain?): DataState<BaseDomain>
    suspend fun getPeopleDetail(baseDomain: BaseDomain?): DataState<BaseDomain>

}
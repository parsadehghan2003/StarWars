package com.parsadehghan.starwars.people.data_source

import com.parsadehghan.base.BaseDomain
import com.parsadehghan.base.interactor.DataState
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(val peopleRemoteService: PeopleRemoteService) : PeopleRepository {

    override suspend fun getPeoples(baseDomain: BaseDomain?): DataState<BaseDomain> {
        return peopleRemoteService.getPeoples(baseDomain)
    }

    override suspend fun getPeopleDetail(baseDomain: BaseDomain?): DataState<BaseDomain> {
        return peopleRemoteService.getPeopleDetail(baseDomain)
    }


}
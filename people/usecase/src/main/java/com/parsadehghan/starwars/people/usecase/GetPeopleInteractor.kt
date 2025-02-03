package com.parsadehghan.starwars.people.usecase

import com.parsadehghan.base.BaseDomain
import com.parsadehghan.base.interactor.DataState
import com.parsadehghan.base.interactor.UseCase
import com.parsadehghan.starwars.people.data_source.PeopleRepository
import javax.inject.Inject

open class GetPeopleInteractor @Inject constructor(val repository: PeopleRepository) : UseCase<BaseDomain>() {
    override suspend fun run(params: BaseDomain?): DataState<BaseDomain> {
        return repository.getPeopleDetail(params)
    }
}
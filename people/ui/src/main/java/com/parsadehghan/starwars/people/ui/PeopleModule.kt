package com.parsadehghan.starwars.people.ui

import com.parsadehghan.starwars.people.data_source.PeopleRemoteService
import com.parsadehghan.starwars.people.data_source.PeopleRepository
import com.parsadehghan.starwars.people.data_source.PeopleRepositoryImpl
import com.parsadehghan.starwars.people.usecase.GetPeoplesInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object PeopleModule {

    @Provides
    @ViewModelScoped
    fun providePeopleRepository(
        peopleRemoteService: PeopleRemoteService
    ): PeopleRepository {
        return PeopleRepositoryImpl(peopleRemoteService)
    }

    @Provides
    @ViewModelScoped
    fun getPeoplesInteractor(peopleRepository: PeopleRepository): GetPeoplesInteractor {
        return GetPeoplesInteractor(peopleRepository)
    }

}
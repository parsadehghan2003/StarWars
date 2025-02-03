package com.parsadehghan.trysedalia.login.framework

import com.parsadehghan.gateway.StarWarsApiService
import com.parsadehghan.starwars.people.data_source.PeopleRemoteService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object PeopleFrameWorkModule {


    @Provides
    @ViewModelScoped
    fun providePeopleRemoteService(starWarsApiService: StarWarsApiService): PeopleRemoteService {
        return PeopleRemoteServiceImpl(starWarsApiService)
    }


}
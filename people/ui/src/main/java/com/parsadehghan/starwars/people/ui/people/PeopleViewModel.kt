package com.parsadehghan.starwars.people.ui.people;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parsadehghan.base.interactor.DataState
import com.parsadehghan.starwars.people.domian.GetCharacterDetailsObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import com.parsadehghan.starwars.people.usecase.GetPeopleInteractor
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val getPeoplesInteractor: GetPeopleInteractor
) : ViewModel() {
    private val _uiState: MutableStateFlow<PeopleDetailUiState> =
        MutableStateFlow(PeopleDetailUiState(isLoading = true))

    // why its using like this? look at https://www.youtube.com/watch?v=mNKQ9dc1knI
    val uiState: StateFlow<PeopleDetailUiState> =
        _uiState.asStateFlow()

    fun loadPeople(id: String) {
        getPeoplesInteractor.call(GetCharacterDetailsObject.GetCharacterDetailsRequest(id)).onEach {
            when(it){
                is DataState.Data -> {
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            characterDetail = it.data as GetCharacterDetailsObject.GetCharacterDetailsResponse
                        )
                    }
                }
                is DataState.Error -> {
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            errorMessage = it.errorModel.message
                        )
                    }
                }
            }

        }.launchIn(viewModelScope)
    }


}

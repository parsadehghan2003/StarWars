package com.parsadehghan.starwars.people.ui.people_list;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parsadehghan.starwars.people.domian.GetCharacterListObject
import com.parsadehghan.starwars.people.usecase.GetPeoplesInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import com.parsadehghan.base.interactor.DataState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

@HiltViewModel
class PeopleListViewModel @Inject constructor(
    private val getPeoplesInteractor: GetPeoplesInteractor
) : ViewModel() {
    private val _uiState: MutableStateFlow<PeopleListUiState> =
        MutableStateFlow(PeopleListUiState(isLoading = false))

    // why its using like this? look at https://www.youtube.com/watch?v=mNKQ9dc1knI
    val uiState: StateFlow<PeopleListUiState> =
        _uiState.onStart {
            loadPeoples()
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            PeopleListUiState(isLoading = true)
        )

    var nextPage: Int? = null
    var isLoading = false
    var search: String? = null

    fun loadPeoples() {
        getPeoples()
    }

    fun loadNextPage() {
        if (!isLoading && nextPage != null) {
            _uiState.update { state -> state.copy(isLoading = false, isLoadingMore = true) }
            getPeoples()
        }
    }

    fun searchPeople(search: String) {
        _uiState.update { state ->

            state.copy(
                isLoading = false,
                listOfPeoples = emptyList(),
                isLoadingMore = false,
                hasMore = true)

        }
        search.ifEmpty {
            this.search = null
            loadPeoples()
        }
        if (this.search != search) {
            this.search = search
            nextPage = null
            loadPeoples()
        }
    }

    private fun getPeoples() {
        isLoading = true
        getPeoplesInteractor.call(GetCharacterListObject.GetCharacterListRequest(nextPage,search)).onEach {
            when (it) {
                is DataState.Data -> {
                    val data = (it.data as GetCharacterListObject.GetCharacterListResponse)
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            listOfPeoples = if (state.isLoadingMore) state.listOfPeoples + data.characters else data.characters,
                            isLoadingMore = false,
                            hasMore = data.nextPage != null
                        )
                    }
                    nextPage = data.nextPage
                    isLoading = false
                }

                is DataState.Error -> {
//                    _uiState.value =
//                        PeopleListUiState.Error(it.errorModel.message ?: "Unknown error")
                    nextPage = null
                    isLoading = false
                }
            }
        }.launchIn(viewModelScope)
    }
}

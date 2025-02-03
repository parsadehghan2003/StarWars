package com.parsadehghan.starwars.people.ui.people_list

import com.parsadehghan.starwars.core.Character

/**
 * A sealed hierarchy describing the state of the text generation.
 */

data class PeopleListUiState(
    val isLoading: Boolean,
    val errorMessage: String? = null,
    val isLoadingMore: Boolean = false,
    val hasMore: Boolean = true,
    val search: String? = null,
    val listOfPeoples: List<Character> = emptyList(),
)
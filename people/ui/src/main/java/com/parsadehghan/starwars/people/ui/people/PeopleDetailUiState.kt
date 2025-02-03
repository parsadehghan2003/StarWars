package com.parsadehghan.starwars.people.ui.people

import com.parsadehghan.starwars.core.Character
import com.parsadehghan.starwars.core.CharacterDetail
import com.parsadehghan.starwars.people.domian.GetCharacterDetailsObject

/**
 * A sealed hierarchy describing the state of the text generation.
 */

data class PeopleDetailUiState(
    val isLoading: Boolean,
    val errorMessage: String? = null,
    val characterDetail: GetCharacterDetailsObject.GetCharacterDetailsResponse? = null
)
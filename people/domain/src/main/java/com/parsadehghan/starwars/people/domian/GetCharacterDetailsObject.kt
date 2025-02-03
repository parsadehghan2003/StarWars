package com.parsadehghan.starwars.people.domian;

import com.parsadehghan.base.ActionType
import com.parsadehghan.base.BaseDomain

sealed class GetCharacterDetailsObject : BaseDomain {

    open class GetCharacterDetailsRequest(
        val characterId: String
    ) : GetCharacterDetailsObject() {
        override val actionType: ActionType
            get() = ActionType.GET_CHARACTER_DETAILS_REQUEST
    }

    open class GetCharacterDetailsResponse(
        val name: String,
        val height: String,
        val mass: String,
        val hairColor: String,
        val skinColor: String,
        val eyeColor: String,
        val birthYear: String,
        val gender: String,
        val homeworld: String,
        val films: List<String>,
        val species: List<String>,
        val vehicles: List<String>,
        val starships: List<String>
    ) : GetCharacterDetailsObject() {
        override val actionType: ActionType
            get() = ActionType.GET_CHARACTER_DETAILS_RESPONSE
    }
}

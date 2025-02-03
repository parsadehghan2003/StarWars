package com.parsadehghan.starwars.people.domian;

import com.parsadehghan.base.ActionType
import com.parsadehghan.base.BaseDomain
import com.parsadehghan.starwars.core.Character

sealed class GetCharacterListObject : BaseDomain {

    open class GetCharacterListRequest(
        val page: Int? = null,
        val search: String? = null
    ) : GetCharacterListObject() {
        override val actionType: ActionType
            get() = ActionType.GET_CHARACTER_LIST_REQUEST
    }

    open class GetCharacterListResponse(
        val characters: List<Character>,
        val nextPage: Int?,
    ) : GetCharacterListObject() {
        override val actionType: ActionType
            get() = ActionType.GET_CHARACTER_LIST_RESPONSE
    }

}


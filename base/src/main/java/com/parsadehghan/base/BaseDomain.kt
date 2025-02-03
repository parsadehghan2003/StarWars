package com.parsadehghan.base

interface BaseDomain {
    val actionType: ActionType
}

enum class ActionType {
    ERROR,
    DATA_CLASS,
    GET_CHARACTER_DETAILS_REQUEST,
    GET_CHARACTER_DETAILS_RESPONSE,
    GET_CHARACTER_LIST_REQUEST,
    GET_CHARACTER_LIST_RESPONSE
}

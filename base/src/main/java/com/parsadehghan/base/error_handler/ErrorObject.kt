package com.parsadehghan.base.error_handler

import com.parsadehghan.base.ActionType
import com.parsadehghan.base.BaseDomain

data class ErrorObject(
    val type:ErrorType = ErrorType.NOT_DEFINED,
    val message: String? = null
) : BaseDomain {
    override val actionType: ActionType
        get() = ActionType.ERROR
}
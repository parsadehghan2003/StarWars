package com.parsadehghan.base.interactor

import com.parsadehghan.base.error_handler.ErrorModel

sealed class DataState<T> (
    open val data: T? = null,
    open val errorModel: ErrorModel? = null
) {

    data class Error<T>(
        override val errorModel: ErrorModel,
        override var data: T? = null
    ) : DataState<T>(data,errorModel)


    data class Data<T>(
        override val data: T? = null
    ) : DataState<T>(data)

}
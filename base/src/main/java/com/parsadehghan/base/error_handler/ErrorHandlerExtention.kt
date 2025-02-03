package com.parsadehghan.base.error_handler

import com.parsadehghan.base.BaseDomain
import com.parsadehghan.base.interactor.DataState

fun errorDataState(errorHandler: ErrorHandler): DataState.Error<BaseDomain> =
    DataState.Error(errorHandler.createError())

fun dataStateInternalErrorHandler(errorCode : Int): DataState.Error<BaseDomain> =
    errorDataState(InternalErrorHandler(errorCode))

fun dataStateRemoteErrorHandler(errorCode : Int): DataState.Error<BaseDomain> =
    errorDataState(HttpErrorHandler(errorCode))

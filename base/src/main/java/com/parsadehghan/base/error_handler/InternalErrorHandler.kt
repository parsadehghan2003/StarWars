package com.parsadehghan.base.error_handler

class InternalErrorHandler(val errorCode:Int): ErrorHandler() {
    override fun createErrorStatus(): IError {
      return InternalError(errorCode)
    }
}
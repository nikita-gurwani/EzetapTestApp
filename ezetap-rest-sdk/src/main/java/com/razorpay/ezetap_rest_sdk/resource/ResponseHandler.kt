package com.razorpay.ezetap_rest_sdk.resource

import com.google.gson.Gson
import com.google.gson.JsonParser
import com.razorpay.ezetap_rest_sdk.resource.Resource
import retrofit2.HttpException
import java.io.IOException
import java.lang.reflect.Type
import java.net.SocketTimeoutException

enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1),
    Unauthorized(401),
    Generic(457),
    NotFound(404),
    BadRequest(400),
    ContentUnavailable(451)
}

enum class ErrorCodeText(val text: String) {
    Timeout("Timeout"),
    Unauthorized("Unauthorized"),
    Generic("Something went wrong"),
    NotFound("Not found"),
    BadRequest("Bad Request");

    companion object {
        fun findErrorCode(key: String?): Int {
            return when (key) {
                Timeout.text -> ErrorCodes.SocketTimeOut.code
                Unauthorized.text -> ErrorCodes.Unauthorized.code
                NotFound.text -> ErrorCodes.NotFound.code
                BadRequest.text -> ErrorCodes.BadRequest.code
                else -> ErrorCodes.Generic.code
            }
        }
    }
}

open class ResponseHandler {
    fun <T : Any, P : Any> handleSuccess(data: T): Resource<T, P> {
        return Resource.success(data)
    }

    fun <T : Any, P : Any> handleException(exception: Exception, type: Type): Resource<T, P> {
        return when (exception) {
            is HttpException -> Resource.error(getErrorMessage(exception.code()), getHttpError(exception, type))
            is SocketTimeoutException -> Resource.error(getErrorMessage(ErrorCodes.SocketTimeOut.code), null)
            else -> Resource.error(getErrorMessage(Int.MAX_VALUE), null)
        }
    }

    fun <T : Any, P : Any> handleError(exception: Exception, type: Type): Resource<T, P> {
        return when (exception) {
            is HttpException -> Resource.error(exception.code().toString(), getHttpError(exception, type))
            is IOException -> Resource.error(ErrorCodes.SocketTimeOut.code.toString(), null)
            is SocketTimeoutException -> Resource.error(ErrorCodes.SocketTimeOut.code.toString(), null)
            else -> Resource.error(ErrorCodes.NotFound.code.toString(), null)
        }
    }

    private fun <P : Any> getHttpError(exception: HttpException, type: Type): P? {
        return if (exception.code() == ErrorCodes.Generic.code || exception.code() == ErrorCodes.ContentUnavailable.code) {
            val errorBody = exception.response()?.errorBody()?.string()
            val error = Gson().fromJson<P>(JsonParser().parse(errorBody), type)
            error
        } else {
            null
        }
    }

    private fun getErrorMessage(code: Int): String {
        return when (code) {
            ErrorCodes.SocketTimeOut.code -> ErrorCodeText.Timeout.text
            ErrorCodes.Unauthorized.code -> ErrorCodeText.Unauthorized.text
            ErrorCodes.NotFound.code -> ErrorCodeText.NotFound.text
            ErrorCodes.BadRequest.code -> ErrorCodeText.BadRequest.text
            else -> ErrorCodeText.Generic.text
        }
    }
}
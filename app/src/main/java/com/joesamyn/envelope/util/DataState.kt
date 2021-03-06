package com.joesamyn.envelope.util

/**
 * Sealed class is like an enum for classes. We can use it to change state
 * on the fly and monitor asynchronous requests
 */
sealed class DataState<out R> {

    /**
     * Success state informs the UI that the request was successful
     */
    data class Success<out T>(val data: T): DataState<T>()

    /**
     * Error state, something went wrong while making request
     */
    data class Error(val exception: Exception): DataState<Nothing>()

    /**
     * Loading state, data is not yet available
     */
    object Loading: DataState<Nothing>()

    /**
     * Failed state indicates a failed network request (not 200 ok)
     */
    object Failed: DataState<Nothing>()

    /**
     * User input is invalid
     */
    data class Invalid<out String>(val message: String): DataState<String>()
}
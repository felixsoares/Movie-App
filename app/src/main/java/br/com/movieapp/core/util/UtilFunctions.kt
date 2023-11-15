package br.com.movieapp.core.util

import timber.log.Timber

object UtilFunctions {

    private const val TAG = "MovieApp"

    fun logError(message: String) {
        Timber.tag(TAG).e("Error -> $message")
    }

    fun logInfo(message: String) {
        Timber.tag(TAG).e("Info -> $message")
    }

}
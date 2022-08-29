package com.ynk.leagueranked.api.remote

import retrofit2.Response
import com.ynk.leagueranked.utils.Resource

/**
 * Base data source
 *
 * @constructor Create empty Base data source
 */
abstract class BaseDataSource {

    /**
     * Get result
     *
     * @param T
     * @param call
     * @receiver
     * @return
     */
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {

        return Resource.error("YNKERROR Network call has failed for a following reason: $message")
    }

}
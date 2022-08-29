package com.ynk.leagueranked.utils

/**
 * Resource
 *
 * @param T
 * @property status
 * @property data
 * @property message
 * @constructor Create empty Resource
 */

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    /**
     * Status
     *
     * @constructor Create empty Status
     */
    enum class Status {
        /**
         * S u c c e s s
         *
         * @constructor Create empty S u c c e s s
         */
        SUCCESS,

        /**
         * E r r o r
         *
         * @constructor Create empty E r r o r
         */
        ERROR,

        /**
         * L o a d i n g
         *
         * @constructor Create empty L o a d i n g
         */
        LOADING
    }

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}
package com.ynk.leagueranked.api.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.ynk.leagueranked.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.http.GET

import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


private const val BASE_URL = "https://yourownapi.api/"
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val intercetpr = HttpLoggingInterceptor().setLevel(if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)


private val client = OkHttpClient.Builder().addInterceptor(intercetpr).build()

private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(
    BASE_URL
).client(client).build()


/**
 * Player api service
 *
 * @constructor Create empty Player api service
 */
interface PlayerApiService {
    /**
     * Get players
     *
     * @return
     */
    @GET("list")
    suspend fun getPlayers(): Response<GetPlayerApiResponse>
}
object PlayerApi{
    val apiService: PlayerApiService by lazy { retrofit.create(PlayerApiService::class.java) }
}
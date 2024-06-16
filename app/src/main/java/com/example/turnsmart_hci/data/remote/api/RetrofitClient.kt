package com.example.turnsmart_hci.data.remote.api

import com.example.turnsmart_hci.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET
import java.util.Date

private val httpLoggingInterceptor = HttpLoggingInterceptor()
    .setLevel(HttpLoggingInterceptor.Level.BODY)

private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(httpLoggingInterceptor)
    .build()

private val gson = GsonBuilder()
    .registerTypeAdapter(Date::class.java, DateTypeAdapter())
    //.registerTypeAdapter(RemoteDevice::class.java, DeviceTypeAdapter())
    .create()

//private val json = Json {ignoreUnknownKeys = true}

private val retrofit = Retrofit.Builder()
    //.addConverterFactory()
    //.baseUrl(BuildConfig.)
    .client(okHttpClient)
    .build()

interface RetrofitClientService {
    @GET("api")
    suspend fun getAnswer() : UInt //Reemplazar Uint por el jasonYoKotlin
}

object RetrofitClient {
    val retrofitClientService: RetrofitClientService by lazy {
        retrofit.create(RetrofitClientService::class.java)
    }

}
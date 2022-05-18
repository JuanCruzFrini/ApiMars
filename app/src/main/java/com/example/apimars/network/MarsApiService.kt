package com.example.apimars.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
//import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

class MarsApiService {

    companion object {
        private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"

        private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        private val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))/*ScalarsConverterFactory*/
            .baseUrl(BASE_URL)
            .build()

        interface  MarsApiService{
            @GET(value = "photos")
            suspend fun getPhotos() : List<MarsPhoto>

            @GET(value = "realestate")
            suspend fun getState() : List<MarsState>
        }

        object MarsApi{
            val retrofitService : MarsApiService by lazy {
                retrofit.create(MarsApiService::class.java)
            }
        }
    }
}
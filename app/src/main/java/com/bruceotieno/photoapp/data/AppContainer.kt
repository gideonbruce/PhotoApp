package com.bruceotieno.photoapp.data

import com.bruceotieno.photoapp.network.PhotosApiService
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

/**
 *  dependency injection at application level
 */
interface AppContainer {
    val photosAppRepository: PhotosAppRepository
}

/**
 *  implementation for the dependency injection container at application level
 *
 *  variables are initialized lazily and the same instance is shared across the whole app
 */

class DefaultAppContainer : AppContainer {
    private val baseUrl = "#/"

    /**
     * use the retrofit builder to build a retrofit object using a kotlinx.serialization converter
     */

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    /**
     *  Retrofit service object for creating api calls
     */
    private val retrofitService: PhotosApiService by lazy {
        retrofit.create(PhotosApiService::class.java)
    }

    /**
     *  DI implementation
     */

    override val photosAppRepository: PhotosAppRepository
        NetworkPhotoAppRepository(retrofitService)
}
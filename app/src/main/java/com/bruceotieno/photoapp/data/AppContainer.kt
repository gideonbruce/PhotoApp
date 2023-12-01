package com.bruceotieno.photoapp.data

import com.bruceotieno.photoapp.network.PhotosApiService
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import androidx.compose.runtime.getValue
import kotlin.reflect.KProperty

/**
 *  dependency injection at application level
 */
interface AppContainer {
    val photosAppRepository: PhotoAppRepository
}

/**
 *  implementation for the dependency injection container at application level
 *
 *  variables are initialized lazily and the same instance is shared across the whole app
 */

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https:api.unsplash.com/photos/"

    /**
     * use the retrofit builder to build a retrofit object using a kotlinx.serialization converter
     */

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: PhotosApiService by lazy {
        retrofit.create(PhotosApiService::class.java)
    }


    /**
     *  whats this error 'Property delegate must have a 'getValue(DefaultAppContainer, KProperty*>)' method. None of the following functions are suitable.'
     * */
    override val photosAppRepository: PhotoAppRepository by lazy {
        NetworkPhotosAppRepository(retrofitService)
    }

    //imma try adding import androidx.compose.runtime.getValue for the by


}


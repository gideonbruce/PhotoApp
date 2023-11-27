package com.bruceotieno.photoapp.network

import com.bruceotieno.photoapp.model.PhotosApp
import retrofit2.http.GET

/**
 *  a public interface that exposes the [getPhotos] method
 */

interface PhotosApiService {
    /**
     * return a [List] of [PhotosApp] and this method can be called from a Coroutine.
     * the @GET annotation indicates that the "photos" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("photos")
    suspend fun getPhotos(): List<PhotosAp>
}
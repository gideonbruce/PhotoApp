package com.bruceotieno.photoapp.data

import com.bruceotieno.photoapp.model.PhotosApp
import com.bruceotieno.photoapp.network.PhotosApiService
import androidx.compose.runtime.getValue

/**
 *  repository that fetch photos List from photos api
 */

interface PhotoAppRepository {
    /** Fetches list of photos from api */
    suspend fun getPhotos(): List<PhotosApp>
}


class NetworkPhotosAppRepository(
    private val photosApiService: PhotosApiService
) : PhotoAppRepository {
    override suspend fun getPhotos(): List<PhotosApp> = photosApiService.getPhotos()
}


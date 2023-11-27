package com.bruceotieno.photoapp.data

import com.bruceotieno.photoapp.model.PhotosApp
import com.bruceotieno.photoapp.network.PhotosApiService

/**
 *  repo that fetch photos List from photos api
 */

interface PhotoAppRepository {
    /** Fetches list of photos from api */
    suspend fun getPhotos(): List<PhotosApp>
}

/**
 *  network implementation of repository that fetch photos list
 */
class NetworkPhotosRepository(
    private val photosApiService: PhotosApiService
) : PhotosApiService {
    /** fetch list */
    override suspend fun getPhotos(): List<PhotosApp> = photosApiService.getPhotos()
}
package com.bruceotieno.photoapp.ui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.bruceotieno.photoapp.data.PhotoAppRepository
import com.bruceotieno.photoapp.model.PhotosApp
import com.bruceotieno.photoapp.PhotoApplication


/**
 *  UI state for the Home screen
 */

sealed interface PhotosUiState {
    data class Success(val photos: List<PhotosApp>) : PhotosUiState
    object Error : PhotosUiState
    object Loading : PhotosUiState
}

class PhotosViewModel(private val photoAppRepository: PhotoAppRepository) : ViewModel() {
    /** mutable state that stores the status of the most recent request */
    var photosUiState: PhotosUiState by mutableStateOf(PhotosUiState.Loading)
    private set

    /**
     *  call getPhotos() on init so we can display status immediately
     */
    init {
        getPhotos()
    }
    fun getPhotos() {
        viewModelScope.launch {
            photosUiState = PhotosUiState.Loading
            photosUiState = try {
                PhotosUiState.Success(photoAppRepository.getPhotos())
            } catch (e: IOException) {
                PhotosUiState.Error
            } catch (e: HttpException) {
                PhotosUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PhotosAppApplication)
                val photoAppRepository = application.container.photoAppRepository
                PhotosViewModel(photoAppRepository = photoAppRepository)
            }
        }
    }
}
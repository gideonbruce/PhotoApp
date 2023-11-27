package com.bruceotieno.photoapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * this data class defines photos which include an ID and the image URL.
 */
@Serializable
data class PhotosApp(
    val id: String,
    @SerialName(value = "img_src")
    val imgSrc: String
)
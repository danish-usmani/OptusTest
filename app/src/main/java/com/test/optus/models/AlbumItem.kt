package com.test.optus.models

import java.io.Serializable

data class AlbumItem(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
): Serializable
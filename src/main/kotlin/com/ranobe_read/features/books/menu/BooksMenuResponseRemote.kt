package com.ranobe_read.features.books.menu

import kotlinx.serialization.Serializable

@Serializable
data class BooksMenuResponseRemote(
    val id: Int,
    val name: String,
    val image: String,
    val descript: String?,
    val rating: Double,
    val status: String,
)
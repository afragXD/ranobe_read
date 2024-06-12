package com.ranobe_read.database.books

import kotlinx.serialization.Serializable

@Serializable
data class BookDTO(
    val name: String,
    val enName: String,
    val image: String,
    val descript: String,
    val rating: Int,
    val status: String,
    val chapters: Int,
    val year: Int?,
    val authorName: String,
    val countryName: String,
    val genres: Set<String>,
    val ratingCount: Int,
)

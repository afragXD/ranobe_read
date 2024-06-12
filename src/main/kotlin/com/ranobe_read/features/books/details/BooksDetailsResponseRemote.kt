package com.ranobe_read.features.books.details

import kotlinx.serialization.Serializable

@Serializable
data class BooksDetailsResponseRemote(
    val id: Int,
    val name: String,
    val image: String,
    val descript: String,
    val rating: Double,
    val status: String,
    val chapters: Int,
    val year: Int,
    val authorName: String,
    val countryName: String,
    val genres: Set<String>,
    val ratingCount: Int,
)
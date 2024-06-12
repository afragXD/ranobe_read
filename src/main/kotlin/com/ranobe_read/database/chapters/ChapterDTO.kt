package com.ranobe_read.database.chapters

data class ChapterDTO(
    val bookId: Int,
    val chapterNumber: Int,
    val chapterName: String,
    val chapterText: String,
)

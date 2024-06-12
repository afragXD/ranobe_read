package com.ranobe_read.features.books

import com.ranobe_read.features.books.details.BooksDetailsController
import com.ranobe_read.features.books.menu.BooksMenuController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureBooksRouting() {
    routing {
        route("/books"){
            get("/menu") {
                val booksController = BooksMenuController(call)
                booksController.menuBooks()
            }
            get("/details") {
                val booksController = BooksDetailsController(call)
                booksController.detailBooks()
            }
        }
    }
}
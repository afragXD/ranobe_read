package com.ranobe_read.features.books.details

import com.ranobe_read.database.books.Books
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

class BooksDetailsController(val call: ApplicationCall) {
    suspend fun detailBooks(){

        val id = call.request.queryParameters["id"]

        if (id != null){
            val booksList = Books.getBookDetailsById(id.toInt())
            if (booksList != null) {
                call.respond(booksList)
            }else{
                call.respondText("Ошибка поиска", status = HttpStatusCode.InternalServerError)
            }
        }else{
            call.respondText("Ошибка входных данных", status = HttpStatusCode.InternalServerError)
        }


    }
}
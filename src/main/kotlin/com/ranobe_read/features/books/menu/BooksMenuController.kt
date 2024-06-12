package com.ranobe_read.features.books.menu

import com.ranobe_read.database.books.Books
import io.ktor.server.application.*
import io.ktor.server.response.*
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.SortOrder

class BooksMenuController(val call: ApplicationCall) {
    suspend fun menuBooks(){

        val limit = call.request.queryParameters["limit"]
        val sortReq = call.request.queryParameters["sort"]
        val sortColumn = call.request.queryParameters["column"]
        var sort: SortOrder = SortOrder.DESC
        if (sortReq.equals("ASC"))
            sort = SortOrder.ASC

        var column: Column<*> = Books.rating
        if (sortColumn.equals("chapters")){
            column = Books.chapters
        }else if (sortColumn.equals("year")){
            column = Books.year
        }

        if (limit != null){
            val booksList = Books.selectAllBooks(limit.toInt(), column to sort)
            call.respond(booksList)
        }else{
            val booksList = Books.selectAllBooks(10, column to sort)
            call.respond(booksList)
        }


    }
}
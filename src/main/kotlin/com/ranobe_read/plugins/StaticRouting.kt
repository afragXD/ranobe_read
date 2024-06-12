package com.ranobe_read.plugins

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Application.configureStaticRouting() {
    routing {
        staticResources("/photos", "static/photos"){
            default("per_id_1.jpg")
        }
        //staticResources("/static/books", "static/books/res/novel"){
        //}
        staticResources("/videos", "static/videos"){
            //preCompressed(CompressedFileType.GZIP, CompressedFileType.BROTLI)
            default("protect_my_balls.mp4")
        }
    }
}

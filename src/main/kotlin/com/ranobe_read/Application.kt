package com.ranobe_read

import com.ranobe_read.features.books.configureBooksRouting
import com.ranobe_read.plugins.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.compression.*
import org.jetbrains.exposed.sql.Database

fun main(args: Array<String>) {

    Database.connect(
        url = "jdbc:mariadb://192.168.0.152:3306/ranobe_read",
        driver = "org.mariadb.jdbc.Driver",
        user = "arisen",
        password = System.getenv("maria_pass")
    )

    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    install(Compression){
        gzip {
            //ContentType.Video.Any
            ContentType.Image.Any
            println(1.0)
            minimumSize(1024)
        }
    }

    configureSerialization()
    configureHTTP()
    configureRouting()
    configureStaticRouting()

    configureBooksRouting()
}

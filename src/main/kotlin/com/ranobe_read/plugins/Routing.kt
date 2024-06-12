package com.ranobe_read.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable


@Serializable
data class Request(val text: String)

@Serializable
data class Response(val Status: Boolean)

@Serializable
data class ResponseRofl(val TextRofl: String)


fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("--- больно")
        }
        // Static plugin. Try to access `/static/index.html`
        static("/static") {
            resources("static")
        }
        post("/event") {
            val request = call.receive<Request>()

            println("--------------------------------------------------------------------------------")
            println(request.text)
            if (request.text.equals("--")){
                call.respond(HttpStatusCode.OK, Response(Status = true))
            }else if (request.text.equals("Я хочу сказать это --!@")){
                call.respond(HttpStatusCode.OK, Response(Status = true))
            }

            //call.respond(HttpStatusCode.OK, Response(Status = true))
        }
        post("/rofl") {
            val request = call.receive<Request>()

            println("--------------------------------------------------------------------------------")
            println(request.text)
            if (request.text.equals("--")){
                call.respond(HttpStatusCode.OK, ResponseRofl(TextRofl = "--"))
            }else if (request.text.equals("Я хочу сказать это --!@")){
                call.respond(HttpStatusCode.OK, ResponseRofl(TextRofl = "ПЛОТНО --!"))
            } else{
                call.respond(HttpStatusCode.OK, ResponseRofl(TextRofl = request.text + " АХАХАХАХАХАХАХАХАХАХАХАХАХАХА"))
            }

            //call.respond(HttpStatusCode.OK, Response(Status = true))
        }
    }
}

package com.picpay.desafio.android

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

open class BasePrepareRobot {

    val server = MockWebServer()

    object MockServer{
        const val serverPort = 8080

        val successResponse by lazy {
            val body =
                "[{\"id\":1001,\"name\":\"Eduardo Santos\",\"img\":\"https://randomuser.me/api/portraits/men/9.jpg\",\"username\":\"@eduardo.santos\"}]"

            MockResponse()
                .setResponseCode(200)
                .setBody(body)
        }

        val errorResponse by lazy { MockResponse().setResponseCode(404) }
    }

    fun initServer () {
        server.start(MockServer.serverPort)
    }

    fun stopServer() {
        server.close()
    }
}
package com.example.edward.socketiochat

import android.app.Application


import java.net.URISyntaxException

import io.socket.client.IO
import io.socket.client.Socket

/**
 * Created by edward on 2/12/18.
 */

class ChatApplications : Application() {

//    companion object {
//        var socket: Socket? = null
//            private set
//    }

    private lateinit var socket: Socket;

    init {
        try {
            socket = IO.socket(Constants.CHAT_SERVER_URL)
        } catch (e: URISyntaxException) {
            throw RuntimeException(e)
        }

    }

    public fun getSocket(): Socket{
        return socket!!;
    }
}

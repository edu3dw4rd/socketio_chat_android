package com.example.edward.socketiochat;

import android.app.Application;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

/**
 * Created by edward on 2/13/18.
 */

public class ChatApplication extends Application {
    private Socket mSocket;
    {
        try{
            mSocket = IO.socket(Constants.CHAT_SERVER_URL);
        } catch(URISyntaxException e){
            throw new RuntimeException(e);
        }
    }

    public Socket getSocket(){
        return mSocket;
    }
}

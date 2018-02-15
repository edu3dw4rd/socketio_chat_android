package com.example.edward.socketiochat

/**
 * Created by edward on 2/12/18.
 */

class Messages {
    companion object {
        const val TYPE_MESSAGE: Int = 0;
        const val TYPE_LOG: Int = 1;
        const val TYPE_ACTION: Int = 2;
    }

    private var mType: Int ?= null;
    private var mMessage: String ?= null;
    private var mUsername: String ?= null;

    public fun getType(): Int?{
        return mType;
    }

    public fun getMessage(): String?{
        return mMessage;
    }

    public fun getUsername(): String?{
        return mUsername;
    }

    class Builder{
        private final var mType: Int ?= null;
        private var mUsername: String ?= null;
        private var mMessage: String ?= null;

        constructor(type: Int){
            mType = type;
        }

        public fun username(username: String): Builder{
            mUsername = username;
            return this;
        }

        public fun message(message: String): Builder{
            mMessage = message;
            return this;
        }

        public fun build(): Messages {
            var message: Messages = Messages();
            message.mType = mType;
            message.mUsername = mUsername;
            message.mMessage = mMessage;

            return message;
        }
    }

}

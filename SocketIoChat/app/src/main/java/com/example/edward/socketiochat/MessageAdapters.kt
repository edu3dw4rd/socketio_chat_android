package com.example.edward.socketiochat

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by edward on 2/12/18.
 */

class MessageAdapters(): RecyclerView.Adapter<MessageAdapters.ViewHolder>(){

    private var mMessages: List<Messages> ?= null;
    private lateinit var musernameColors: IntArray;

    constructor(context: Context, messages: List<Messages>): this(){
        mMessages = messages;
        musernameColors = context.resources.getIntArray(R.array.username_colors);
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var layout: Int = -1;

        when(viewType){
            Messages.TYPE_MESSAGE -> layout = R.layout.item_message;
            Messages.TYPE_LOG -> layout = R.layout.item_log;
            Messages.TYPE_ACTION -> layout = R.layout.item_action;
        }

        var v: View = LayoutInflater.from(parent?.context).inflate(layout, parent, false);

        return ViewHolder(v);
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        var message: Messages = mMessages?.get(position) as Messages;
        holder?.setMessage(message.getMessage() as String);
        holder?.setUsername(message.getUsername() as String);
    }


    override fun getItemCount(): Int {
        return mMessages?.size as Int;
    }

    override fun getItemViewType(position: Int): Int {
        return mMessages?.get(position)?.getType() as Int;
    }

    inner class ViewHolder: RecyclerView.ViewHolder{
        private var mUsernameView: TextView ?= null;
        private var mMessageView: TextView ?= null;

        constructor(itemView: View): super(itemView){
            mUsernameView = itemView.findViewById<TextView>(R.id.username);
            mMessageView = itemView.findViewById<TextView>(R.id.message);
        }

        public fun setUsername(username: String){
            if(mUsernameView == null) return;

            mUsernameView?.setText(username);
            mUsernameView?.setTextColor(getUsernameColor(username));
        }

        public fun setMessage(message: String){
            if(mMessageView == null) return;
            mMessageView?.setText(message);
        }

        private fun getUsernameColor(username: String): Int{
            var hash: Int = 7;

            for(i in 0..username.length){
                hash = username.codePointAt(i) + (hash shl 5) - hash;
            }

            var index: Int = Math.abs(hash % musernameColors.size);

            return musernameColors[index];
        }
    }
}

package com.example.sample_message_client.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sample_message_client.R
import com.example.sample_message_client.data.Message
import kotlinx.android.synthetic.main.item_message.view.*
import kotlinx.android.synthetic.main.item_message_my.view.*
import java.text.SimpleDateFormat
import java.util.*

class MessagesAdapter(
    private val myUsername: String
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val messages = mutableListOf<Message>()

    companion object {
        private const val MESSAGE_TYPE = 0
        private const val MY_MESSAGE_TYPE = 1
    }

    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(message: Message) = itemView.apply {
            senderName.text = message.author
            messageText.text = message.text
            senderDate.text = SimpleDateFormat("HH:mm MMM:dd", Locale.getDefault()).format(message.date)
        }

    }

    class MyMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(message: Message) = itemView.apply {
            myName.text = message.author
            myMessageText.text = message.text
            mySenderDate.text = SimpleDateFormat("HH:mm MMM:dd", Locale.getDefault()).format(message.date)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == MY_MESSAGE_TYPE) {
            val vMyMessage =
                LayoutInflater.from(parent.context).inflate(R.layout.item_message_my, parent, false)
            MyMessageViewHolder(vMyMessage)
        } else {
            val vMessage =
                LayoutInflater.from(parent.context).inflate(R.layout.item_message, parent, false)
            MyMessageViewHolder(vMessage)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (messages[position].author == myUsername) {
            MY_MESSAGE_TYPE
        } else {
            MESSAGE_TYPE
        }
    }

    override fun getItemCount() = messages.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(getItemViewType(position) == MY_MESSAGE_TYPE) {
            (holder as MyMessageViewHolder ).bind(messages[position])
        }else{
            (holder as MessageViewHolder ).bind(messages[position])
        }

}

}
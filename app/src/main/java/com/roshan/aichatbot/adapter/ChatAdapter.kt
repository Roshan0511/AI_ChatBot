package com.roshan.aichatbot.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.roshan.aichatbot.databinding.BotItemBinding
import com.roshan.aichatbot.databinding.UserItemBinding
import com.roshan.aichatbot.model.MessageModel
import com.roshan.aichatbot.utilities.Preferences
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ChatAdapter(private val context: Context, private val list: ArrayList<MessageModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val ITEM_SENT = 1
    private val ITEM_RECEIVE = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == 1){
            val binding = UserItemBinding.inflate(LayoutInflater.from(context), parent, false)
            UserViewHolder(binding)
        } else {
            val binding = BotItemBinding.inflate(LayoutInflater.from(context), parent, false)
            BotViewHolder(binding)
        }
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model: MessageModel = list[position]

        when(model.sender){
            "user" -> {
                val viewHolder = holder as UserViewHolder

                // Set Message
                viewHolder.userBinding!!.tvMessage.text = model.message

                // Set User Image
                if (Preferences.getValues(context, "gender") == "Male"){
                    viewHolder.userBinding.ivUserMale.visibility = View.VISIBLE
                    viewHolder.userBinding.ivUserFemale.visibility = View.GONE
                } else if (Preferences.getValues(context, "gender") == "Female"){
                    viewHolder.userBinding.ivUserMale.visibility = View.GONE
                    viewHolder.userBinding.ivUserFemale.visibility = View.VISIBLE
                }
            }
            "bot" -> {
                val viewHolder = holder as BotViewHolder

                // Set message
                viewHolder.botBinding!!.tvMessage.text = model.message
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = list[position]

        return if (currentMessage.sender == "user"){
            ITEM_SENT
        } else {
            ITEM_RECEIVE
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class UserViewHolder(val userBinding: UserItemBinding?) : RecyclerView.ViewHolder(userBinding!!.root)

    inner class BotViewHolder(val botBinding: BotItemBinding?) : RecyclerView.ViewHolder(botBinding!!.root)
}
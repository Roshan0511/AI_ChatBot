package com.roshan.aichatbot.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.roshan.aichatbot.adapter.ChatAdapter
import com.roshan.aichatbot.databinding.FragmentChatBinding
import com.roshan.aichatbot.model.MessageModel
import com.roshan.aichatbot.utilities.Constants
import org.json.JSONException


class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding
    private lateinit var list: ArrayList<MessageModel>
    private lateinit var adapter: ChatAdapter
    private val USER_KEY: String = "user"
    private val BOT_KEY: String = "bot"

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentChatBinding.inflate(inflater, container, false)

        initView()

        binding.btnSend.setOnClickListener {
            if (binding.etMessage.text.trim().toString().isEmpty()) {
                binding.etMessage.error = "Please enter your message"
            } else {
                val message = binding.etMessage.text.trim().toString()

                list.add(MessageModel(message, USER_KEY))

                binding.ivGreeting.visibility = View.GONE

                adapter.notifyDataSetChanged()

                callBotApi(message)
                binding.chatRv.smoothScrollToPosition(list.size)
                binding.etMessage.text = null
            }
        }

        return binding.root
    }


    // Initialization

    private fun initView() {
        list = ArrayList()
        adapter = context?.let { ChatAdapter(it, list) }!!

        binding.chatRv.layoutManager = LinearLayoutManager(context)
        binding.chatRv.adapter = adapter
    }


    // Call Bot Api for get response

    @SuppressLint("HardwareIds", "NotifyDataSetChanged")
    private fun callBotApi(message: String) {

        val androidId = Settings.Secure.getString(requireContext().contentResolver, Settings.Secure.ANDROID_ID)

        val url = "http://api.brainshop.ai/get?bid=${Constants.BID}&key=${Constants.API_KEY}&uid=$androidId&msg=$message"

        val queue: RequestQueue = Volley.newRequestQueue(context)

        val jsonObjectRequest =
            JsonObjectRequest(Request.Method.GET, url, null,
                { response ->
                    try {

                        val botResponse = response!!.getString("cnt")
                        list.add(MessageModel(botResponse, BOT_KEY))

                        adapter.notifyDataSetChanged()

                        binding.chatRv.smoothScrollToPosition(list.size)

                    } catch (e: JSONException) {
                        Toast.makeText(context, e.message!!.toString(), Toast.LENGTH_SHORT).show()
                        e.printStackTrace()
                    }
                }) {
                    Toast.makeText(context, "No response from the bot..", Toast.LENGTH_SHORT).show()
            }

        queue.add(jsonObjectRequest)
    }
}
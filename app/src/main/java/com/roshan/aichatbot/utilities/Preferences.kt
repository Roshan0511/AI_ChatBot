package com.roshan.aichatbot.utilities

import android.content.Context

class Preferences {

    companion object {
        private const val pref = "pref"

        fun setValues(context: Context, gender: String, isLoggedIn: Boolean) {
            val sharedPreferences = context.getSharedPreferences(pref, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            editor.putString("gender", gender)
            editor.putBoolean("isLoggedIn", isLoggedIn)

            editor.apply()
        }

        fun getValues(context: Context, data: String) : String? {
            val sharedPreferences = context.getSharedPreferences(pref, Context.MODE_PRIVATE)

            val gender = sharedPreferences.getString("gender", "")
            val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

            when(data) {
                "gender" -> return gender.toString()
                "isLoggedIn" -> return isLoggedIn.toString()
            }

            return null
        }
    }
}
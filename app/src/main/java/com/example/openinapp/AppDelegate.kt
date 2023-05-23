package com.example.openinapp


import android.content.Context
import com.example.openinapp.Constants.PREFS_NAME
import com.example.openinapp.Constants.TOKEN_KEY

class AppDelegate {
    companion object {
        fun saveTokenToLocalStorage(context: Context, token: String) {
            val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString(TOKEN_KEY, token)
            editor.apply()
        }
    }
}

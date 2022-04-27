package com.example.demoapplication

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserManager @Inject constructor(
    private val sharedPrefs: SharedPreferences
) {

    var userInput: String
        get() = sharedPrefs.getString(USER_INPUT, "").orEmpty()
        set(value) = sharedPrefs.edit { putString(USER_INPUT, value) }

    companion object {
        const val USER_INPUT = "USER_INPUT"
    }

}
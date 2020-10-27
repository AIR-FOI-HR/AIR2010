package com.escaper.escaper.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.circuitmessing.R

class Preferences(context: Context) {
    private val prefsName: String = context.getString(R.string.prefs)
    private val prefs: SharedPreferences = context.getSharedPreferences(prefsName, 0)

    var isConnected: Boolean
        get() = prefs.getBoolean("isConnected", false)
        set(value) = prefs.edit().putBoolean("isConnected", value).apply()

    var username: String
        get() = prefs.getString("username", "")!!
        set(value) = prefs.edit().putString("username", value).apply()

    var email: String
        get() = prefs.getString("email", "")!!
        set(value) = prefs.edit().putString("email", value).apply()

    var lastName: String
        get() = prefs.getString("lastName", "")!!
        set(value) = prefs.edit().putString("lastName", value).apply()

    var firstName: String
        get() = prefs.getString("firstName", "")!!
        set(value) = prefs.edit().putString("firstName", value).apply()

    var city: String
        get() = prefs.getString("city", "")!!
        set(value) = prefs.edit().putString("city", value).apply()

    var user_id: String
        get() = prefs.getString("user_id", "")!!
        set(value) = prefs.edit().putString("user_id", value).apply()

    var access_token: String
        get() = prefs.getString("access_token", "")!!
        set(value) = prefs.edit().putString("access_token", value).apply()

    /***********************************************************************
     ******************* USER SETTINGS *************************************
     ***********************************************************************/

    var mobileNotif: Boolean
        get() = prefs.getBoolean("mobile_notif", false)
        set(value) = prefs.edit().putBoolean("mobile_notif", value).apply()

    var mailNotif: Boolean
        get() = prefs.getBoolean("mail_notif", false)
        set(value) = prefs.edit().putBoolean("mail_notif", value).apply()

    var nightMode: Boolean
        get() = prefs.getBoolean("night_mode", false)
        set(value) = prefs.edit().putBoolean("night_mode", value).apply()

    var privateAccount: Boolean
        get() = prefs.getBoolean("private_account", false)
        set(value) = prefs.edit().putBoolean("private_account", value).apply()

    var userLocalisation: Boolean
        get() = prefs.getBoolean("user_localisation", false)
        set(value) = prefs.edit().putBoolean("user_localisation", value).apply()

    var appFontSizeInitialIndex: Int
        get() = prefs.getInt("app_font_size_initial_index", 3)
        set(value) = prefs.edit().putInt("app_font_size_initial_index", value).apply()
    var appFontSizeCurrentIndex: Int
        get() = prefs.getInt("app_font_size_current_index", 3)
        set(value) = prefs.edit().putInt("app_font_size_current_index", value).apply()

    var policyAccepted: Boolean
        get() = prefs.getBoolean("policy_accepted", false)
        set(value) = prefs.edit().putBoolean("policy_accepted", value).apply()

}
package me.deyinejiddou.skeleton.ui.utils.prefs

import android.content.Context
import android.content.SharedPreferences
import me.deyinejiddou.skeleton.BuildConfig


class Prefs(context: Context) {

    val PREFS_FILENAME = "me.deyine-jiddou.skeleton.prefs"

    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var userToken: String?
        get() = prefs.getString(BuildConfig.USER_TOKEN, null)
        set(value) = prefs.edit().putString(BuildConfig.USER_TOKEN, value).apply()

}
package com.nampv.ads.data.banners

import android.content.Context
import com.nampv.ads.domain.shared.IAdsSharedPref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AdsSharedPrefImpl(context: Context) : IAdsSharedPref {

    private val sharedPref =
        context.getSharedPreferences(IAdsSharedPref.FILE_NAME, Context.MODE_PRIVATE)

    private val edit get() = sharedPref.edit()

    override suspend fun getBannerSettingJson(): String {
        return withContext(Dispatchers.IO) {
            sharedPref.getString(IAdsSharedPref.BANNER_SETTING_JSON, "") ?: ""
        }
    }

    override suspend fun setBannerSettingJson(json: String) {
        withContext(Dispatchers.IO) {
            edit.putString(IAdsSharedPref.BANNER_SETTING_JSON, json).commit()
        }
    }

}
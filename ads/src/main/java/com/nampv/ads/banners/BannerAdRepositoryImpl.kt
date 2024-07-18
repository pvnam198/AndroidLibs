package com.nampv.ads.banners

import com.google.gson.Gson
import com.nampv.ads.domain.banners.BannerSetting
import com.nampv.ads.domain.banners.IBannerAdRepository
import com.nampv.ads.domain.shared.IAdsSharedPref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BannerAdRepositoryImpl(
    private val adsSharedPref: IAdsSharedPref,
    private val defaultBannerSettings: Map<String, BannerSetting>? = null
) : IBannerAdRepository {

    private val bannerSettings = HashMap<String, BannerSetting>()

    override suspend fun getBannerSetting(id: String): BannerSetting? {
        return withContext(Dispatchers.Main) {
            bannerSettings[id] ?: defaultBannerSettings?.get(id)
        }
    }

    override suspend fun setBannerSettings(json: String) {
        if (json.isEmpty()) return
        adsSharedPref.setBannerSettingJson(json)
        collectBannerSettings(json)
    }

    private suspend fun collectBannerSettings(json: String) {
        withContext(Dispatchers.Default) {
            if (json.isEmpty()) return@withContext
            try {
                val bannerConfigs =
                    Gson().fromJson(json, Array<BannerSettingEntity>::class.java)
                bannerConfigs.forEach {
                    bannerSettings[it.id] = it.toBannerSetting()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}
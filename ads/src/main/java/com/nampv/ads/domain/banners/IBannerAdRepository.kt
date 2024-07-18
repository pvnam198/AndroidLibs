package com.nampv.ads.domain.banners

interface IBannerAdRepository {

    suspend fun getBannerSetting(id: String): BannerSetting?

    suspend fun setBannerSettings(json: String)

}
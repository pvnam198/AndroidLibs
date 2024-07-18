package com.nampv.ads.banners

import com.google.gson.annotations.SerializedName
import com.nampv.ads.domain.banners.BannerSetting

data class BannerSettingEntity(
    @SerializedName("id")
    val id: String,
    @SerializedName("show")
    val show: Boolean,
    @SerializedName("collapsible")
    val collapsible: Boolean
) {
    fun toBannerSetting() = BannerSetting(id, show, collapsible)
}
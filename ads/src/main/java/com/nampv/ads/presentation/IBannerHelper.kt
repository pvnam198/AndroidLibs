package com.nampv.ads.presentation

import android.view.ViewGroup

interface IBannerHelper {
    suspend fun showIfNeedElseHide(
        viewGroup: ViewGroup,
        adId: String,
        collapsible: Boolean
    )
}
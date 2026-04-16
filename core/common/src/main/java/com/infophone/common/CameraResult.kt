package com.infophone.common

import android.net.Uri

data class CameraResult(
    val uris: List<Uri>,
    val text: String
)
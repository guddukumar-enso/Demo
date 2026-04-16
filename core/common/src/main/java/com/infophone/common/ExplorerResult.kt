package com.infophone.common

data class ExplorerResult(
    val type: ExplorerType,
    val data: Any // This will be Uri or List<Uri>
)
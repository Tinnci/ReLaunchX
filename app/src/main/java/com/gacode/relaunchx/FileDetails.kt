package com.gacode.relaunchx

import java.util.Date

data class FileDetails(
    val name: String,
    val displayName: String,
    val extension: String,
    val directoryName: String,
    val fullPathName: String,
    val type: FsItemType,
    val date: Date,
    val size: Long,
    val reader: String
)

enum class FsItemType {
    File,
    Directory
} 
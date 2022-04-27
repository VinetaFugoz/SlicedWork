package com.slicedwork.slicedwork.util

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.documentfile.provider.DocumentFile

fun isValidUri(context: Context, uri: Uri): Boolean {
    val file = DocumentFile.fromSingleUri(context, uri)
    return file?.exists() == true
}
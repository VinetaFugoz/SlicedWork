package com.slicedwork.slicedwork.util

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.core.content.ContextCompat
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class PermissionUtil(private val registry: ActivityResultRegistry): DefaultLifecycleObserver {

    private lateinit var launcher: ActivityResultLauncher<String>

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        launcher = registry.register("key", owner, RequestPermission()) {}
    }

    private fun hasPermission(permission: String, context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context, permission) == PackageManager.PERMISSION_GRANTED
    }

    fun requestPermission(permission: String, context: Context) {
        if (hasPermission(permission, context))
            Log.i("Permission", "$permission permission is already granted")
        else
            launcher.launch(permission)
    }
}


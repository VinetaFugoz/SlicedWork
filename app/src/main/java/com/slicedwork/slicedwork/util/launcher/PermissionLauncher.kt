package com.slicedwork.slicedwork.util.launcher

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions
import androidx.core.content.ContextCompat
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class PermissionLauncher(private val registry: ActivityResultRegistry): DefaultLifecycleObserver {

    private lateinit var launcher: ActivityResultLauncher<Array<String>>

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        launcher = registry.register("permissions", owner, RequestMultiplePermissions()) {}
    }

    private fun hasThesePermissions(permissions: Array<String>, context: Context): Boolean {
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
            return false
        }
        return true
    }

    fun requestPermissions(permissions: Array<String>, context: Context) {
        if (hasThesePermissions(permissions, context))
            Log.i("Permissions", "$permissions is already granted")
        else
            launcher.launch(permissions)
    }
}


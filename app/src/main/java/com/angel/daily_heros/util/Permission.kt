package com.angel.daily_heros.util

import android.Manifest
import android.content.Context
import com.gun0912.tedpermission.TedPermissionResult
import com.tedpark.tedpermission.rx2.TedRx2Permission
import io.reactivex.functions.Consumer

fun requestPermissionForCamera(context: Context, callback: () -> Unit) {
    TedRx2Permission.with(context)
        .setPermissions(
            Manifest.permission.CAMERA
        )
        .request()
        .subscribe(
            Consumer { tedPermissionResult: TedPermissionResult ->
                if (tedPermissionResult.isGranted) {
                    callback()
                } else {
                }
            },
            Consumer { throwable: Throwable? -> }
        )
}
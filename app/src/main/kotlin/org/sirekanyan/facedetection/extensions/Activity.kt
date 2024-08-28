package org.sirekanyan.facedetection.extensions

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS

fun Activity.openAppSettings() {
    val uri = Uri.fromParts("package", packageName, null)
    startActivity(Intent(ACTION_APPLICATION_DETAILS_SETTINGS, uri))
}

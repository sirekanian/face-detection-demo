package org.sirekanyan.facedetection.extensions

import androidx.camera.core.CameraSelector
import org.sirekanyan.facedetection.model.CameraType

fun CameraSelector.toCameraType(): CameraType =
    when (this) {
        CameraSelector.DEFAULT_BACK_CAMERA -> CameraType.BACK
        CameraSelector.DEFAULT_FRONT_CAMERA -> CameraType.FRONT
        else -> CameraType.NONE
    }

fun CameraType.toCameraSelector(): CameraSelector =
    when (this) {
        CameraType.FRONT -> CameraSelector.DEFAULT_FRONT_CAMERA
        CameraType.BACK -> CameraSelector.DEFAULT_BACK_CAMERA
        CameraType.NONE -> CameraSelector.DEFAULT_BACK_CAMERA
    }

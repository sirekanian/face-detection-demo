package org.sirekanyan.facedetection.model

import androidx.annotation.StringRes
import org.sirekanyan.facedetection.R

enum class CameraType(@StringRes val titleRes: Int) {

    FRONT(R.string.button_toggle_camera_front),
    BACK(R.string.button_toggle_camera_back),
    NONE(R.string.app_name);

    val next: CameraType
        get() = when (this) {
            FRONT -> BACK
            BACK -> FRONT
            NONE -> NONE
        }
}

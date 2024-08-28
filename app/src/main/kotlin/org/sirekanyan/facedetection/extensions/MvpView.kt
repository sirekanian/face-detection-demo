package org.sirekanyan.facedetection.extensions

import android.content.Context
import org.sirekanyan.facedetection.mvp.MvpView

val MvpView<*>.context: Context
    get() = binding.root.context

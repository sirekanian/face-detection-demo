package org.sirekanyan.facedetection.mvp

import androidx.viewbinding.ViewBinding

interface MvpView<T : ViewBinding> {
    val binding: T
}

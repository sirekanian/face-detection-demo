package org.sirekanyan.facedetection.mvp

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

abstract class MvpBasePresenter<V : MvpView<*>> : DefaultLifecycleObserver {

    lateinit var view: V

    override fun onStop(owner: LifecycleOwner) {
        // todo: cancel all network requests
    }
}

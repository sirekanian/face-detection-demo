package org.sirekanyan.facedetection

import org.sirekanyan.facedetection.MainPresenter.Router
import org.sirekanyan.facedetection.model.CameraType
import org.sirekanyan.facedetection.mvp.MvpBasePresenter
import org.sirekanyan.facedetection.mvp.MvpPresenter

interface MainPresenter : MvpPresenter {

    fun onCameraInitialized(currentCamera: CameraType, availableCameras: Set<CameraType>)

    interface Router {
        fun switchToCamera(camera: CameraType)
    }
}

class MainPresenterImpl(
    private val router: Router,
) : MvpBasePresenter<MainView>(),
    MainPresenter,
    MainView.Callbacks {

    private var currentCamera = CameraType.NONE

    override fun onCameraInitialized(currentCamera: CameraType, availableCameras: Set<CameraType>) {
        this.currentCamera = currentCamera
        when (availableCameras.size) {
            0 -> view.showError(R.string.error_no_camera_found)
            1 -> view.hideToggleButton()
            else -> view.showToggleButton()
        }
        updateViews()
    }

    override fun onToggleButtonClicked() {
        currentCamera = currentCamera.next
        router.switchToCamera(currentCamera)
        updateViews()
    }

    private fun updateViews() {
        view.setToggleButtonTitle(currentCamera.next.titleRes)
    }
}

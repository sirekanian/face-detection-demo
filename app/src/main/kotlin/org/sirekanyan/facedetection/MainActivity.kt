package org.sirekanyan.facedetection

import android.Manifest
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.CameraSelector.DEFAULT_BACK_CAMERA
import androidx.camera.core.CameraSelector.DEFAULT_FRONT_CAMERA
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.Analyzer
import androidx.camera.mlkit.vision.MlKitAnalyzer
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.lifecycle.Lifecycle.State
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.mlkit.vision.face.FaceDetection
import kotlinx.coroutines.guava.await
import kotlinx.coroutines.launch
import org.sirekanyan.facedetection.databinding.MainActivityBinding
import org.sirekanyan.facedetection.extensions.openAppSettings
import org.sirekanyan.facedetection.extensions.showToast
import org.sirekanyan.facedetection.extensions.toCameraSelector
import org.sirekanyan.facedetection.extensions.toCameraType
import org.sirekanyan.facedetection.model.CameraType
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity(), MainPresenter.Router {

    private lateinit var binding: MainActivityBinding
    private lateinit var presenter: MainPresenter
    private lateinit var camera: CameraController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (checkSelfPermission(this, Manifest.permission.CAMERA) != PERMISSION_GRANTED) {
            // todo: request permission dialog
            showToast(R.string.error_no_camera_permission)
            openAppSettings()
            finish()
            return
        }
        presenter = bindPresenter()
        camera = bindCameraController()
        awaitCameraInitialization()
    }

    private fun bindPresenter(): MainPresenter =
        MainPresenterImpl(this)
            .also { lifecycle.addObserver(it) }
            .also { it.view = MainViewImpl(binding, it) }

    private fun bindCameraController(): CameraController =
        LifecycleCameraController(baseContext)
            .also { it.bindToLifecycle(this) }
            .also {
                it.setImageAnalysisAnalyzer(
                    Executors.newSingleThreadExecutor(),
                    createFaceAnalyzer(),
                )
            }

    private fun createFaceAnalyzer(): Analyzer {
        val faceDetector = FaceDetection.getClient()
        return MlKitAnalyzer(
            listOf(faceDetector),
            ImageAnalysis.COORDINATE_SYSTEM_VIEW_REFERENCED,
            ContextCompat.getMainExecutor(this),
        ) { analyzeResult ->
            analyzeResult?.getValue(faceDetector)?.firstOrNull()?.boundingBox?.let { box ->
                binding.faceBoxView.setBox(box)
            }
        }
    }

    private fun awaitCameraInitialization() {
        lifecycleScope.launch {
            repeatOnLifecycle(State.STARTED) {
                camera.initializationFuture.await()
                binding.cameraPreviewView.controller = camera
                presenter.onCameraInitialized(
                    currentCamera = camera.cameraSelector.toCameraType(),
                    availableCameras = listOf(DEFAULT_BACK_CAMERA, DEFAULT_FRONT_CAMERA)
                        .filter(camera::hasCamera)
                        .map(CameraSelector::toCameraType)
                        .toSet(),
                )
            }
        }
    }

    override fun switchToCamera(camera: CameraType) {
        this.camera.cameraSelector = camera.toCameraSelector()
    }
}

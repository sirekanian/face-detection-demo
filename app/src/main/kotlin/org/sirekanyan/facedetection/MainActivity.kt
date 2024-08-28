package org.sirekanyan.facedetection

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.Analyzer
import androidx.camera.mlkit.vision.MlKitAnalyzer
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.core.content.ContextCompat
import com.google.mlkit.vision.face.FaceDetection
import org.sirekanyan.facedetection.databinding.MainActivityBinding
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.cameraPreviewView.controller = createCameraController()
    }

    private fun createCameraController(): CameraController {
        val cameraController = LifecycleCameraController(baseContext)
        cameraController.setImageAnalysisAnalyzer(
            Executors.newSingleThreadExecutor(),
            createFaceAnalyzer(),
        )
        cameraController.bindToLifecycle(this)
        return cameraController
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
}

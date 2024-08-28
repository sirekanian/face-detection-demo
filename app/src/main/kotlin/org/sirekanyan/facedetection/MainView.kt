package org.sirekanyan.facedetection

import android.view.View
import androidx.annotation.StringRes
import org.sirekanyan.facedetection.databinding.MainActivityBinding
import org.sirekanyan.facedetection.extensions.context
import org.sirekanyan.facedetection.extensions.showToast
import org.sirekanyan.facedetection.mvp.MvpView

interface MainView : MvpView<MainActivityBinding> {

    fun showError(@StringRes message: Int)
    fun showToggleButton()
    fun hideToggleButton()
    fun setToggleButtonTitle(@StringRes title: Int)

    interface Callbacks {
        fun onToggleButtonClicked()
    }
}

class MainViewImpl(
    override val binding: MainActivityBinding,
    callbacks: MainView.Callbacks,
) : MainView {

    private val toggleButton = binding.toggleButton

    init {
        toggleButton.setOnClickListener { callbacks.onToggleButtonClicked() }
    }

    override fun showError(@StringRes message: Int) {
        context.showToast(message)
    }

    override fun showToggleButton() {
        toggleButton.visibility = View.VISIBLE
    }

    override fun hideToggleButton() {
        toggleButton.visibility = View.GONE
    }

    override fun setToggleButtonTitle(@StringRes title: Int) {
        toggleButton.setText(title)
    }
}

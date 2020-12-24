package io.devbits.onboarding.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.isInvisible
import io.devbits.onboarding.R
import io.devbits.onboarding.databinding.StepperLayoutBinding

class StepperLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    val binding: StepperLayoutBinding

    init {
        val layoutInflater = LayoutInflater.from(context)
        binding = StepperLayoutBinding.inflate(layoutInflater, this, true)
    }

    fun updateButtons(position: Int) {
        when (position) {
            0 -> {
                binding.buttonLeft.isInvisible = true
                binding.buttonRight.setIconResource(R.drawable.ic_right)
                binding.buttonRight.text = context.getString(R.string.text_button_start)
            }
            binding.tabOnboardingItems.tabCount - 1 -> {
                binding.buttonLeft.isInvisible = false
                binding.buttonRight.icon = null
                binding.buttonRight.text = context.getString(R.string.text_button_done)
            }
            else -> {
                binding.buttonRight.setIconResource(R.drawable.ic_right)
                binding.buttonLeft.isInvisible = false
                binding.buttonRight.text = context.getString(R.string.text_button_next)
                binding.buttonLeft.text = context.getString(R.string.text_button_back)
            }
        }
    }

}
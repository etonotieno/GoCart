package io.devbits.onboarding.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.isInvisible
import com.google.android.material.tabs.TabLayout
import io.devbits.onboarding.R
import io.devbits.onboarding.databinding.StepperLayoutBinding

class StepperLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: StepperLayoutBinding

    val tabLayout: TabLayout
        get() = binding.tabOnboardingItems

    init {
        val layoutInflater = LayoutInflater.from(context)
        binding = StepperLayoutBinding.inflate(layoutInflater, this, true)

        binding.buttonEnd.setIconResource(R.drawable.ic_right)
        binding.buttonEnd.text = context.getString(R.string.text_button_start)
    }

    fun updateButtons(position: Int) {
        when (position) {
            0 -> {
                binding.buttonStart.isInvisible = true
                binding.buttonEnd.setIconResource(R.drawable.ic_right)
                binding.buttonEnd.text = context.getString(R.string.text_button_start)
            }
            binding.tabOnboardingItems.tabCount - 1 -> {
                binding.buttonStart.isInvisible = false
                binding.buttonEnd.icon = null
                binding.buttonEnd.text = context.getString(R.string.text_button_done)
            }
            else -> {
                binding.buttonEnd.setIconResource(R.drawable.ic_right)
                binding.buttonStart.isInvisible = false
                binding.buttonEnd.text = context.getString(R.string.text_button_next)
                binding.buttonStart.text = context.getString(R.string.text_button_back)
            }
        }
    }

}
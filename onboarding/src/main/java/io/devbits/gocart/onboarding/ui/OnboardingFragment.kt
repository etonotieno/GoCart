package io.devbits.gocart.onboarding.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.content.ContextCompat
import androidx.core.content.edit
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import io.devbits.gocart.composeui.theme.GoCartTheme
import io.devbits.gocart.onboarding.ui.adapter.OnboardingItemAdapter
import io.devbits.gocart.core.R as coreR


class OnboardingFragment : Fragment() {

    private val sharedPreferences: SharedPreferences
        get() {
            return requireContext().applicationContext.getSharedPreferences(
                "Onboarding",
                Context.MODE_PRIVATE
            )
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                GoCartTheme {
                    OnboardingScreen(onCompleteOnboarding = {
                        goToHomeScreen()
                    })
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val window = requireActivity().window
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true

        window.statusBarColor =
            ContextCompat.getColor(requireContext(), coreR.color.white_70)

        (activity as? AppCompatActivity)?.supportActionBar?.hide()
    }

    private fun goToHomeScreen() {
        sharedPreferences.edit { putBoolean("ONBOARDING", true) }
        val controller = findNavController()
        controller.navigate(OnboardingFragmentDirections.toHomeAction())
    }
}
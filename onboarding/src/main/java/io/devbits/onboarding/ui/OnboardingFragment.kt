package io.devbits.onboarding.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.edit
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import dev.chrisbanes.insetter.applySystemWindowInsetsToPadding
import io.devbits.onboarding.R
import io.devbits.onboarding.databinding.FragmentOnboardingBinding
import io.devbits.onboarding.ui.adapter.OnboardingItemAdapter
import io.devbits.onboarding.ui.model.OnboardingItem
import io.devbits.core.R as coreR


class OnboardingFragment : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!

    private val onboardingItemAdapter = OnboardingItemAdapter()

    private lateinit var tabLayoutMediator: TabLayoutMediator
    private lateinit var onPageChangeCallback: ViewPager2.OnPageChangeCallback

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
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEdgeToEdge()
        val window = requireActivity().window
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true

        window.statusBarColor =
            ContextCompat.getColor(requireContext(), coreR.color.white_70)

        (activity as? AppCompatActivity)?.supportActionBar?.hide()

        onboardingItemAdapter.submitList(
            listOf(
                OnboardingItem(
                    R.drawable.ic_fresh_produce,
                    getString(R.string.text_onboarding_fresh_produce_title),
                    getString(R.string.text_onboarding_fresh_produce_description)
                ),
                OnboardingItem(
                    R.drawable.ic_fast_delivery,
                    getString(R.string.text_onboarding_fast_delivery_title),
                    getString(R.string.text_onboarding_fast_delivery_description)
                ),
                OnboardingItem(
                    R.drawable.ic_easy_payments,
                    getString(R.string.text_onboarding_easy_payments_title),
                    getString(R.string.text_onboarding_easy_payments_description)
                ),
            )
        )

        binding.stepperView.showSkipButton = true

        binding.viewPagerOnboardingItems.adapter = onboardingItemAdapter

        onPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.stepperView.updateButtonSteps(position)
            }
        }

        tabLayoutMediator = TabLayoutMediator(
            binding.stepperView.tabLayout,
            binding.viewPagerOnboardingItems
        ) { _, _ -> }

        binding.viewPagerOnboardingItems.registerOnPageChangeCallback(onPageChangeCallback)

        tabLayoutMediator.attach()

        binding.stepperView.onSkipInto {
            goToHomeScreen()
        }

        binding.stepperView.onStartClicked {
            var position = binding.viewPagerOnboardingItems.currentItem
            if (position > 0) {
                binding.viewPagerOnboardingItems.currentItem = --position
            }
        }

        binding.stepperView.onEndClicked {
            var position = binding.viewPagerOnboardingItems.currentItem
            val tabCount = binding.stepperView.tabCount
            if (position < tabCount) {
                binding.viewPagerOnboardingItems.currentItem = ++position
            }

            if (position == tabCount) {
                goToHomeScreen()
            }
        }
    }

    private fun goToHomeScreen() {
        sharedPreferences.edit { putBoolean("ONBOARDING", true) }
        val controller = findNavController()
        controller.navigate(OnboardingFragmentDirections.toHomeAction())
    }

    override fun onDestroyView() {
        binding.viewPagerOnboardingItems.unregisterOnPageChangeCallback(onPageChangeCallback)
        tabLayoutMediator.detach()
        _binding = null
        super.onDestroyView()
    }

    private fun setupEdgeToEdge() {
        binding.root.applySystemWindowInsetsToPadding(top = true)
        binding.stepperView.applySystemWindowInsetsToPadding(bottom = true)
    }

    companion object {
        fun newInstance() = OnboardingFragment()
    }

}
package io.devbits.onboarding.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import dev.chrisbanes.insetter.applySystemWindowInsetsToPadding
import io.devbits.onboarding.R
import io.devbits.onboarding.databinding.FragmentOnboardingBinding
import io.devbits.onboarding.ui.adapter.OnboardingItemAdapter
import io.devbits.onboarding.ui.model.OnboardingItem

class OnboardingFragment : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!

    private val onboardingItemAdapter = OnboardingItemAdapter()

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
        setEdgeToEdge()

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

        binding.viewPagerOnboardingItems.adapter = onboardingItemAdapter

        binding.viewPagerOnboardingItems.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.stepperView.updateButtons(position)
            }
        })

        TabLayoutMediator(
            binding.stepperView.binding.tabOnboardingItems,
            binding.viewPagerOnboardingItems
        ) { _, _ ->
        }.attach()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setEdgeToEdge() {
        binding.stepperView.applySystemWindowInsetsToPadding(bottom = true)
    }

    companion object {
        fun newInstance() = OnboardingFragment()
    }

}
package io.devbits.gocart.ui.main

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import io.devbits.gocart.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    private val sharedPreferences: SharedPreferences
        get() {
            return requireContext().applicationContext.getSharedPreferences(
                "Onboarding",
                Context.MODE_PRIVATE
            )
        }
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navController = findNavController()
        if (!onboardingComplete()) {
            navController.navigate(MainFragmentDirections.actionMainFragmentToOnboarding())
        }
    }

    private fun onboardingComplete(): Boolean = sharedPreferences.getBoolean("ONBOARDING", false)

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}
package io.devbits.gocart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import io.devbits.gocart.databinding.MainActivityBinding
import io.devbits.gocart.core.R as coreR
import io.devbits.gocart.onboarding.R as onboardingR

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setupNavigation()
        setContentView(binding.root)
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.mainFragment -> setTheme(coreR.style.Theme_GoCart)
                onboardingR.id.onboardingFragment -> setTheme(coreR.style.Theme_GoCart_NoActionBar)
            }
        }
    }
}
package io.devbits.gocart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.chrisbanes.insetter.setEdgeToEdgeSystemUiFlags
import io.devbits.gocart.databinding.MainActivityBinding
import io.devbits.gocart.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setEdgeToEdge()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    private fun setEdgeToEdge() {
        binding.root.setEdgeToEdgeSystemUiFlags()
    }
}
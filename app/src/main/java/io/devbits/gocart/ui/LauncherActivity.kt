package io.devbits.gocart.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import dev.chrisbanes.insetter.setEdgeToEdgeSystemUiFlags
import io.devbits.gocart.MainActivity

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setEdgeToEdge()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun setEdgeToEdge() {
        findViewById<View>(android.R.id.content).setEdgeToEdgeSystemUiFlags()
    }
}
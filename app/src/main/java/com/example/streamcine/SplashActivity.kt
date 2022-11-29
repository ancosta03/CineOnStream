package com.example.streamcine

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.lifecycleScope
import com.example.streamcine.ui.theme.StreamCineTheme
import kotlinx.coroutines.delay
import com.example.streamcine.R

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenCreated {
            delay(3000)
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        setContent {
            StreamCineTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    displaySplashscreen()
                }
            }
        }
    }
}

@Composable
fun displaySplashscreen() {
    Text(text = stringResource(R.string.splashscreen))
    //TODO Mettre le logo
}
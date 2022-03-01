package com.rodriguesporan.gardener.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.rodriguesporan.gardener.R
import com.rodriguesporan.gardener.viewmodels.SplashViewModel
import kotlinx.coroutines.flow.collect

class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        setObservers()
        viewModel.onCreateSplashActivity()
    }

    private fun setObservers() {
        lifecycleScope.launchWhenCreated {
            viewModel.uiState.collect { uiState ->
                if (uiState is SplashViewModel.UiState.CreatedState) {
                    startMainActivity()
                }
            }
        }
    }

    private fun startMainActivity() {
        Intent(this, MainActivity::class.java).run(::startActivity)
        finish()
    }
}
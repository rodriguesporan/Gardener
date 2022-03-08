package com.rodriguesporan.gardener.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.rodriguesporan.gardener.viewmodels.ForestViewModel
import com.rodriguesporan.gardener.R
import com.rodriguesporan.gardener.adapters.ForestAdapter
import com.rodriguesporan.gardener.data.FloraUiState
import com.rodriguesporan.gardener.data.Message
import com.rodriguesporan.gardener.data.PlantUiState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ForestFragment : Fragment() {

    private val viewModel by activityViewModels<ForestViewModel>()
    private val recyclerView by lazy { view?.findViewById<RecyclerView>(R.id.recyclerview) }
    private val progressBar by lazy { view?.findViewById<ProgressBar>(R.id.progress_bar) }
    private val forestAdapter = ForestAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.forest_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViews()
        setUpObservers()
        viewModel.onViewCreated()
        // TODO: Add accessibility
    }

    private fun setUpViews() {
        recyclerView?.adapter = forestAdapter
    }

    private fun setUpObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            // The block passed to repeatOnLifecycle is executed when the lifecycle
            // is at least STARTED and is cancelled when the lifecycle is STOPPED.
            // It automatically restarts the block when the lifecycle is STARTED again.
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Safely collect from locationFlow when the lifecycle is STARTED
                // and stops collection when the lifecycle is STOPPED
                viewModel.uiState.collect { uiState ->
                    handleStateChanges(uiState)
                }
            }
        }
    }

    private fun handleStateChanges(currentUiState: FloraUiState) {
        currentUiState.run {
            handleProgressBar(isLoading)
            updateForestAdapter(plantList)
            handleUserMessages(userMessages)
        }
    }

    private fun handleProgressBar(isLoading: Boolean) {
        progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun updateForestAdapter(plantList: List<PlantUiState>) {
        forestAdapter.submitList(plantList)
    }

    private fun handleUserMessages(userMessages: List<Message>) {
        userMessages.firstOrNull()?.let {
                userMessage ->
            // TODO: Show Snackbar with userMessage
            // Once the message is displayed and
            // dismissed, notify the ViewModel.
            viewModel.userMessageShown(userMessage.id)
        }
    }
}

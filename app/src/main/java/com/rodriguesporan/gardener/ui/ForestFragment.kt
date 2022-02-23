package com.rodriguesporan.gardener.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodriguesporan.gardener.model.ForestViewModel
import com.rodriguesporan.gardener.R

class ForestFragment : Fragment() {

    companion object {
        fun newInstance() = ForestFragment()
    }

    private lateinit var viewModel: ForestViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.forest_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ForestViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
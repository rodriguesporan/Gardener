package com.rodriguesporan.gardener.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.rodriguesporan.gardener.viewmodels.ForestViewModel
import com.rodriguesporan.gardener.R
import com.rodriguesporan.gardener.adapters.ForestAdapter

class ForestFragment : Fragment() {

    private val viewModel by activityViewModels<ForestViewModel>()
    private val recyclerView by lazy { view?.findViewById<RecyclerView>(R.id.recyclerview) }
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
        // setUpAccessibility()
    }

    private fun setUpViews() {
        recyclerView?.adapter = forestAdapter
    }

    private fun setUpObservers() {
        viewModel.forestPlantList.observe(requireActivity(), {
            forestAdapter.submitList(it)
        })
    }

}
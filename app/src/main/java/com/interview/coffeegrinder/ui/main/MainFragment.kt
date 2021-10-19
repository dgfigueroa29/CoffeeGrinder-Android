package com.interview.coffeegrinder.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.interview.coffeegrinder.R
import com.interview.coffeegrinder.model.Coffee
import dagger.hilt.android.AndroidEntryPoint
import java.lang.ref.WeakReference

@AndroidEntryPoint
class MainFragment : Fragment(), OnSelectItem<Coffee?> {
    private val viewModel: MainViewModel by viewModels()
    private var rvItems: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        viewModel.fetchCoffees()
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rvItems = view.findViewById(R.id.rvItems)
        rvItems?.layoutManager = layoutManager
    }

    private fun setupObserver() {
        viewModel.coffees.observe(viewLifecycleOwner, {
            val ref = WeakReference(requireContext())
            val adapter = CoffeeAdapter(ref, this)
            adapter.setData(it)
            rvItems?.adapter = adapter
        })
        viewModel.coffeeDetail.observe(viewLifecycleOwner, { item ->
            item?.let {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("${it.name} (Only $${it.price})")
                    .setMessage(it.description)
                    .setNegativeButton("Cancel") { _, _ ->
                    }
                    .setPositiveButton("Buy") { _, _ ->
                    }
                    .show()
            }
        })
    }

    override fun onSelectItem(item: Coffee?) {
        item?.let {
            viewModel.fetchCoffee(it.id ?: "0")
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
package com.marina.hammersystems.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.marina.hammersystems.R
import com.marina.hammersystems.app.App
import com.marina.hammersystems.databinding.FragmentPizzaListBinding
import com.marina.hammersystems.presentation.ErrorOnUI
import com.marina.hammersystems.presentation.UiState
import com.marina.hammersystems.presentation.adapter.BannerAdapter
import com.marina.hammersystems.presentation.adapter.CategoriesAdapter
import com.marina.hammersystems.presentation.adapter.ProductListAdapter
import com.marina.hammersystems.presentation.view_model.PizzaListViewModel
import com.marina.hammersystems.presentation.view_model.ViewModelFactory
import javax.inject.Inject

class PizzaListFragment : Fragment(R.layout.fragment_pizza_list) {

    private lateinit var binding: FragmentPizzaListBinding
    private lateinit var bannersRV: RecyclerView
    private lateinit var categoriesRV: RecyclerView
    private lateinit var productsRV: RecyclerView
    private lateinit var bannersListAdapter: BannerAdapter
    private lateinit var categoriesListAdapter: CategoriesAdapter
    private lateinit var productsListAdapter: ProductListAdapter
    private lateinit var viewModel: PizzaListViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as App).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[PizzaListViewModel::class.java]
        binding = FragmentPizzaListBinding.bind(view)
        setupRecyclerView()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.bannersList.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Success -> {
                    bannersListAdapter.submitList(state.data)
                }
                else -> {}
            }
        }

        viewModel.categoriesList.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Success -> {
                    categoriesListAdapter.submitList(state.data)
                }
                else -> {}
            }
        }

        viewModel.productsList.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Success -> {
                    setLoading(false)
                    productsListAdapter.submitList(state.data)

                }
                is UiState.Error -> {
                    if (state.error == ErrorOnUI.INTERNET_CONNECTION) {
                        showSnackbar(getString(R.string.no_connection))
                    } else {
                        showSnackbar(getString(R.string.unknown_error))
                    }
                }
                is UiState.Loading -> {
                    setLoading(true)
                }
                else -> {}
            }
        }


    }

    private fun showSnackbar(info: String) {
        Snackbar.make(
            binding.root,
            info,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun setLoading(isLoading: Boolean) = with(binding) {
        progressBar.isVisible = isLoading
    }

    private fun setupRecyclerView() {
        bannersRV = binding.bannerListRv
        categoriesRV = binding.rvCategories
        productsRV = binding.rvProducts

        bannersListAdapter = BannerAdapter()
        categoriesListAdapter = CategoriesAdapter()
        productsListAdapter = ProductListAdapter()

        bannersRV.adapter = bannersListAdapter
        categoriesRV.adapter = categoriesListAdapter
        productsRV.adapter = productsListAdapter
    }
}
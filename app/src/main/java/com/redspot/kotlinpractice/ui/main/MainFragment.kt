package com.redspot.kotlinpractice.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.redspot.kotlinpractice.R
import com.redspot.kotlinpractice.ui.adapter.MainRecyclerAdapter
import com.redspot.kotlinpractice.databinding.MainFragmentBinding
import com.redspot.kotlinpractice.model.AllCategory
import com.redspot.kotlinpractice.model.AppState
import com.redspot.kotlinpractice.model.entities.Movie
import com.redspot.kotlinpractice.ui.adapter.CategoryItemRecyclerAdapter
import com.redspot.kotlinpractice.ui.details.DetailsFragment

class MainFragment : Fragment(), CategoryItemRecyclerAdapter.Interaction {

    private lateinit var binding: MainFragmentBinding
    private lateinit var mainCategoryRecycle: RecyclerView
    private lateinit var mainRecyclerAdapter: MainRecyclerAdapter

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onClickItem(movie: Movie) {

        val manager = activity?.supportFragmentManager
        manager
            ?.beginTransaction()
            ?.add(R.id.container, DetailsFragment.newInstance(movie))
            ?.addToBackStack("")
            ?.commitAllowingStateLoss()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val observer = Observer<AppState> { renderData(it) }
        viewModel.getLiveData().observe(viewLifecycleOwner, observer)

        viewModel.getCategories()
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Success -> {
                mainLoading.visibility = View.GONE
                setCategoryRecycler(appState.data as List<AllCategory>)
            }
            is AppState.Loading -> {
                mainLoading.visibility = View.VISIBLE
            }
            is AppState.Failure -> {
                mainLoading.visibility = View.GONE
                Snackbar
                    .make(main, appState.msg, Snackbar.LENGTH_INDEFINITE)
                    .setAction("Reload") { viewModel.getCategories() }
                    .show()
            }
        }
    }

    private fun setCategoryRecycler(list: List<AllCategory>) {
        mainCategoryRecycle = binding.mainRecycler
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        mainCategoryRecycle.layoutManager = layoutManager
        mainRecyclerAdapter = MainRecyclerAdapter(context, list, this)
        mainCategoryRecycle.adapter = mainRecyclerAdapter
    }
}
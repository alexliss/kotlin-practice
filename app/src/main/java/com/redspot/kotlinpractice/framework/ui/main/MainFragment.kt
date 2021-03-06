package com.redspot.kotlinpractice.framework.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.redspot.kotlinpractice.R
import com.redspot.kotlinpractice.databinding.MainFragmentBinding
import com.redspot.kotlinpractice.framework.ui.adapter.CategoryItemRecyclerAdapter
import com.redspot.kotlinpractice.framework.ui.adapter.MainRecyclerAdapter
import com.redspot.kotlinpractice.framework.ui.showSnackbar
import com.redspot.kotlinpractice.framework.ui.details.DetailsFragment
import com.redspot.kotlinpractice.framework.ui.hide
import com.redspot.kotlinpractice.framework.ui.show
import com.redspot.kotlinpractice.model.AppState
import com.redspot.kotlinpractice.model.entities.Movie
import com.redspot.kotlinpractice.model.entities.MoviesCategory
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(), CategoryItemRecyclerAdapter.Interaction {

    private lateinit var binding: MainFragmentBinding
    private lateinit var mainCategoryRecycle: RecyclerView
    private lateinit var mainRecyclerAdapter: MainRecyclerAdapter
    private val viewModel: MainViewModel by viewModel()

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onClickItem(movie: Movie) {
            activity?.supportFragmentManager?.apply {
            beginTransaction().apply {
                add(R.id.container, DetailsFragment.newInstance(movie))
                addToBackStack("")
                commitAllowingStateLoss()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val observer = Observer<AppState> { renderData(it) }
        viewModel.getLiveData().observe(viewLifecycleOwner, observer)

        viewModel.getCategories()
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Success -> {
                mainLoading.hide()
                setCategoryRecycler(appState.data as List<MoviesCategory>)
            }
            is AppState.Loading -> {
                mainLoading.show()
            }
            is AppState.Failure -> {
                mainLoading.hide()
                main.showSnackbar(appState.msg, "Reload", { viewModel.getCategories() })
            }
        }
    }

    private fun setCategoryRecycler(list: List<MoviesCategory>) {
        mainCategoryRecycle = binding.mainRecycler
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        mainCategoryRecycle.layoutManager = layoutManager
        mainRecyclerAdapter = MainRecyclerAdapter(context, list, this)
        mainCategoryRecycle.adapter = mainRecyclerAdapter
    }
}
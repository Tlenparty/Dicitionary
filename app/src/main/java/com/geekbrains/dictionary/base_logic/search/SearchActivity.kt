package com.geekbrains.dictionary.base_logic.search

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geekbrains.dictionary.R
import com.geekbrains.dictionary.base_logic.search.adapter.SearchDataRVAdapter
import com.geekbrains.dictionary.databinding.SearchActivityBinding
import com.geekbrains.dictionary.base_logic.BaseDaggerActivity
import com.geekbrains.dictionary.helpers.extensions.isOnline
import com.geekbrains.dictionary.states.SearchState
import com.geekbrains.dictionary.viewmodel.SearchViewModel
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class SearchActivity : BaseDaggerActivity() {

    private val binding: SearchActivityBinding by viewBinding(createMethod = CreateMethod.INFLATE)
    private lateinit var model: SearchViewModel

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var adapter: SearchDataRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        model = viewModelFactory.create(SearchViewModel::class.java)
        model.getLiveData().observe(this, Observer<SearchState> { renderData(it) })
        setListeners()
    }

    fun renderData(state: SearchState) {
        when (state) {
            is SearchState.Loading -> {
                setVisibleView(true)
            }
            is SearchState.Success -> {
                setVisibleView(false)
                adapter.setData(state.data)
            }
            is SearchState.Error -> {
                Snackbar.make(binding.root, state.error.toString(), Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun setListeners() = with(binding) {
        inputLayout.setEndIconOnClickListener {
            val value = binding.inputEditText.text.toString()
            if (value.isEmpty()) {
                binding.inputLayout.error = getString(R.string.search_field_null)
            } else if (!baseContext.isOnline()) {
                Snackbar.make(binding.root, getString(R.string.no_connect), Snackbar.LENGTH_LONG)
                    .show()
            } else {
                model.findWord(value)
            }
        }

        adapter = SearchDataRVAdapter()
        recyclerView.adapter = adapter
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                baseContext,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    private fun setVisibleView(loading: Boolean) = with(binding) {
        progressBar.isVisible = loading
        recyclerView.visibility = if (loading) View.GONE else View.VISIBLE
    }
}
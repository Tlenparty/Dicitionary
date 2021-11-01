package com.geekbrains.dictionary.base_logic.search

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.geekbrains.dictionary.R
import com.geekbrains.dictionary.base_logic.search.adapter.SearchDataListPresenterFactory
import com.geekbrains.dictionary.base_logic.search.adapter.SearchDataRVAdapter
import com.geekbrains.dictionary.databinding.SearchActivityBinding
import com.geekbrains.dictionary.states.SearchState
import com.google.android.material.snackbar.Snackbar

class SearchActivity : AppCompatActivity(), SearchView {

    private lateinit var binding: SearchActivityBinding
    private val presenter = SearchPresenterFactory.create()
    private val adapterPresenter = SearchDataListPresenterFactory.create()
    private lateinit var adapter: SearchDataRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SearchActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //связываем activity с presenter
        presenter.attach(this)

        //навешиваем обработчики
        setListeners()
    }

    override fun onCreateView(parent: View?, name: String, context: Context, attrs: AttributeSet): View? {

        return super.onCreateView(parent, name, context, attrs)
    }

    override fun onDestroy() {
        //отвязываем activity от presenter
        presenter.detach()
        super.onDestroy()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun renderData(state: SearchState) {
        when (state) {
            //процесс загрузки
            is SearchState.Loading -> {
                setVisibleView(true)
            }

            //обработанный ответ
            is SearchState.Success -> {
                setVisibleView(false)
                //обновляем данные в списке найденных переводов
                adapterPresenter.searchData = state.data
                adapter.notifyDataSetChanged()
            }

            //возникшая ошибка
            is SearchState.Error -> {
                Snackbar.make(binding.root, state.error.toString(), Snackbar.LENGTH_LONG).show()
            }
        }
    }

    //навешать различные обработчики
    private fun setListeners() = with(binding) {
        //обработка кнопки перевода
        inputLayout.setEndIconOnClickListener {
            val value = binding.inputEditText.text.toString()
            if (value.isEmpty()) {
                binding.inputLayout.error = getString(R.string.search_field_null)
            } else {
                presenter.findWord(value)
            }
        }

        //инициалзация списка найденных переводов
        adapter = SearchDataRVAdapter(adapterPresenter)
        recyclerView.adapter = adapter
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.addItemDecoration(DividerItemDecoration(baseContext, LinearLayoutManager.VERTICAL))
    }

    //показать или скрыть крутилку и найденные переводы
    private fun setVisibleView(loading: Boolean) = with(binding) {
        progressBar.isVisible = loading
        recyclerView.visibility = if (loading) View.GONE else View.VISIBLE
    }
}
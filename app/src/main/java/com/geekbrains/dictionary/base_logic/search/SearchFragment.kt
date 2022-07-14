package com.geekbrains.dictionary.base_logic.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.geekbrains.dictionary.R
import com.geekbrains.dictionary.base_logic.BaseDiFragment
import com.geekbrains.dictionary.base_logic.favorite.FavoriteFragment
import com.geekbrains.dictionary.base_logic.main.AppActivity
import com.geekbrains.dictionary.base_logic.search.adapter.SearchDataRVAdapter
import com.geekbrains.dictionary.base_logic.search_detail.SearchDetailFragment
import com.geekbrains.dictionary.databinding.SearchFragmentBinding
import com.geekbrains.core_helpers.consts.Scopes
import com.geekbrains.dictionary.extensions.isOnline
import com.geekbrains.dictionary.extensions.showMessage
import com.geekbrains.dictionary.states.SearchState
import com.geekbrains.dictionary.viewmodel.SearchViewModel
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseDiFragment(), SearchView {
    private lateinit var binding: SearchFragmentBinding
    private val model: SearchViewModel by viewModel(qualifier = named(Scopes.SEARCH_VIEW_MODEL))

    private val adapter: SearchDataRVAdapter by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = SearchFragmentBinding.inflate(inflater, container, false)

        //связываем fragment с viewModel
        model.getLiveData().observe(viewLifecycleOwner, Observer<SearchState> { renderData(it) })

        //навешиваем обработчики
        adapter.searchView = this
        setListeners()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        model.getLiveData().removeObservers(viewLifecycleOwner)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //включим поддержку нижнего меню
        val activity: AppActivity? = requireActivity() as? AppActivity
        activity?.setSupportActionBar(binding.bottomAppBar)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.bottom_menu_search_fragment, menu)
    }

    //обработать нижнее меню
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            //избранное
            R.id.menu_favorite_id -> {
                openFragment(FavoriteFragment.newInstance())
            }
        }

        return super.onOptionsItemSelected(item)
    }

    //показать детализацию
    override fun showWordDetail(id: Long) {
        openFragment(SearchDetailFragment.newInstance(id))
    }

    //пометить слово избранным
    override fun changeFavorite(searchDataId: Long, favorite: Boolean) {
        model.changeFavorite(searchDataId, favorite)
    }

    private fun renderData(state: SearchState) {
        when (state) {
            //процесс загрузки
            is SearchState.Loading -> {
                setVisibleView(true)
            }

            //обработанный ответ
            is SearchState.Success -> {
                setVisibleView(false)
                //обновляем данные в списке найденных переводов
                adapter.setData(state.data)
            }

            //изменение избранного
            is SearchState.Favorite -> {
                adapter.updateFavorite(state.searchDataId, state.favorite)
            }

            //возникшая ошибка
            is SearchState.Error -> {
                setVisibleView(false)
                requireContext().showMessage(message = state.error.toString())
            }
        }
    }

    //навешать различные обработчики
    private fun setListeners() = with(binding) {
        //обработка кнопок поиска
        findInternetId.setOnClickListener {
            findWord(value = inputEditText.text.toString(), inInternet = true)
        }
        findDbId.setOnClickListener {
            findWord(value = inputEditText.text.toString(), inInternet = false)
        }

        //инициалзация списка найденных переводов
        recyclerView.adapter = adapter
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))

        //слушатель на поле ввода
        binding.inputEditText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }

            override fun afterTextChanged(s: Editable?) {
                //очищаем ошибку, если полевода не пустое
                s?.let {
                    if (it.isNotEmpty()) {
                        binding.inputLayout.error = null
                    }
                }
            }

        })
    }

    //выполнить поиск слова либо в интернете, либо в базе
    private fun findWord(value: String, inInternet: Boolean) {
        if (value.isEmpty()) {
            binding.inputLayout.error = getString(R.string.search_field_null)
        } else if (!requireContext().isOnline()) {
            requireContext().showMessage(message = getString(R.string.no_connect))
        } else {
            if (inInternet) {
                model.findWord(value)
            } else {
                model.findWordInDB(value)
            }
        }
    }

    //показать или скрыть крутилку и найденные переводы
    private fun setVisibleView(loading: Boolean) = with(binding) {
        progressBar.isVisible = loading
        recyclerView.isVisible = !loading
    }

    //открыть окно
    private fun openFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
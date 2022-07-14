package com.geekbrains.dictionary.base_logic.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.geekbrains.dictionary.R
import com.geekbrains.dictionary.base_logic.BaseDiFragment
import com.geekbrains.dictionary.base_logic.search.SearchView
import com.geekbrains.dictionary.base_logic.search.adapter.SearchDataRVAdapter
import com.geekbrains.dictionary.base_logic.search_detail.SearchDetailFragment
import com.geekbrains.dictionary.data.entities.SearchData
import com.geekbrains.dictionary.databinding.FavoriteFragmentBinding
import com.geekbrains.core_helpers.consts.Scopes
import com.geekbrains.dictionary.extensions.showMessage
import com.geekbrains.dictionary.states.FavoriteState
import com.geekbrains.dictionary.viewmodel.FavoriteViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class FavoriteFragment : BaseDiFragment(), SearchView {

    companion object {
        fun newInstance(): Fragment = FavoriteFragment()
    }

    private lateinit var binding: FavoriteFragmentBinding
    private val viewModel: FavoriteViewModel by viewModel(qualifier = named(Scopes.FAVORITE_VIEW_MODEL))
    private val adapter: SearchDataRVAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FavoriteFragmentBinding.inflate(layoutInflater, container, false)

        //связываем fragment с viewModel
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer<FavoriteState> { renderData(it) })
        viewModel.findFavorite()

        initAdapter()

        return binding.root
    }

    private fun initAdapter(){
        adapter.searchView = this
        with(binding){
            recyclerView.adapter = adapter
            recyclerView.itemAnimator = DefaultItemAnimator()
            recyclerView.addItemDecoration(DividerItemDecoration(requireContext(),LinearLayoutManager.VERTICAL))
        }
    }

    private fun renderData(state: FavoriteState) {
        when (state) {
            //процесс загрузки
            is FavoriteState.Loading -> {
                setVisibleView(true)
            }

            //обработанный ответ
            is FavoriteState.Success -> {
                setVisibleView(false)

                //преобразуем список для адаптера
                val data: List<SearchData> = state.data
                    .map { SearchData(wordTranslates = it, favorite = true) }
                    .toList()

                //обновляем данные в списке найденных переводов
                adapter.setData(data)
            }

            //изменение избранного
            is FavoriteState.Favorite -> {
                adapter.updateFavorite(
                    searchDataId = state.searchDataId,
                    favorite = state.favorite,
                    deleteItem = true
                )
            }

            //возникшая ошибка
            is FavoriteState.Error -> {
                setVisibleView(false)
                requireContext().showMessage(message = state.error.toString())
            }
        }
    }

    //показать или скрыть крутилку и найденные переводы
    private fun setVisibleView(loading: Boolean) = with(binding) {
        progressBar.isVisible = loading
        recyclerView.visibility = if (loading) View.GONE else View.VISIBLE
    }

    //показать детализацию
    override fun showWordDetail(id: Long) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container,SearchDetailFragment.newInstance(id))
            .addToBackStack(null)
            .commit()
    }

    //пометить слово избранным
    override fun changeFavorite(searchDataId: Long, favorite: Boolean) {
        viewModel.changeFavorite(searchDataId, favorite)
    }
}
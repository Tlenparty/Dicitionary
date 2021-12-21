package com.geekbrains.dictionary.base_logic.search_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.geekbrains.dictionary.base_logic.BaseDiFragment
import com.geekbrains.dictionary.data.db.entities.WordTranslates
import com.geekbrains.dictionary.databinding.SearchDetailFragmentBinding
import com.geekbrains.dictionary.helpers.consts.DB
import com.geekbrains.dictionary.helpers.consts.Scopes
import com.geekbrains.dictionary.helpers.extensions.showMessage
import com.geekbrains.dictionary.states.SearchDetailState
import com.geekbrains.dictionary.viewmodel.SearchDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import java.lang.StringBuilder

class SearchDetailFragment : BaseDiFragment() {

    companion object {
        fun newInstance(searchDataId: Long): Fragment = SearchDetailFragment()
            .also {
                it.arguments = bundleOf(DB.SEARCH_DATA_ID to searchDataId)
            }
    }

    private lateinit var binding: SearchDetailFragmentBinding
    private val viewModel: SearchDetailViewModel by viewModel(qualifier = named(Scopes.SEARCH_DETAIL_VIEW_MODEL))

    private val searchDataId by lazy { arguments?.getLong(DB.SEARCH_DATA_ID) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SearchDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //связываем fragment c viewModel
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer<SearchDetailState> { renderData(it) })
        viewModel.findSearchData(searchDataId)
    }

    private fun renderData(state: SearchDetailState) {
        when (state) {
            //процесс загрузки
            is SearchDetailState.Loading -> {
                setVisibleView(true)
            }

            //обратный ответ
            is SearchDetailState.Success -> {
                setVisibleView(false)
            }

            //возникновение ошибки
            is SearchDetailState.Error -> {
                setVisibleView(false)
                requireContext().showMessage(message = state.error.toString())
            }
        }
    }

    //отобразить данные по переводу
    private fun setData(data: WordTranslates) = with(binding) {
        // найденное слово, транскрипция и переводы
        searchTextId.text = data.searchData.finedText
        transcriptionTextId.text = data.translation[0].transcription
        val translateStr = StringBuilder()
        data.translation.forEach { translate ->
            translateStr.append(translate.text).append("; ")
        }
        translateTextId.text = translateStr

        //картинка предпросмотра
        data.translation[0].imageUrl?.let {
            viewModel.loadImage(requireContext(), "https:$it")
        }
    }


    //показать или скрыть крутилку и найденный перевод
    private fun setVisibleView(loading: Boolean) = with(binding) {
        progressBar.isVisible = loading
        data.visibility = if (loading) View.GONE else View.VISIBLE
    }


}
package com.geekbrains.dictionary.base_logic.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.dictionary.data.entities.SearchData
import com.geekbrains.dictionary.databinding.SearchDataItemViewBinding
import java.lang.StringBuilder

class SearchDataRVAdapter(private val searchDataListPresenter: SearchDataListPresenter) :
    RecyclerView.Adapter<SearchDataRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            SearchDataItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        searchDataListPresenter.bindView(holder.apply { currentPosition = position })
    }

    override fun getItemCount(): Int = searchDataListPresenter.getCount()

    inner class ViewHolder(private val binding: SearchDataItemViewBinding) :
        RecyclerView.ViewHolder(binding.root), SearchDataItemView {

        override var currentPosition = -1
        override fun setData(data: SearchData) {
            //задаем слово, которое нашлось для перевода
            binding.searchTextId.text = data.finedText

            //задаем транскрипцию и список переводов
            val translates = data.translates
            binding.transcriptionTextId.text = translates[0].transcription
            val translateStr = StringBuilder()
            translates.forEach { translate ->
                translateStr.append(translate.translation.text).append("; ")
            }
            binding.translateTextId.text = translateStr
        }
    }

}
package com.geekbrains.dictionary.base_logic.search.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.dictionary.data.entities.SearchData
import com.geekbrains.dictionary.databinding.SearchDataItemViewBinding
import com.geekbrains.dictionary.helpers.Constants
import java.lang.StringBuilder

class SearchDataRVAdapter: RecyclerView.Adapter<SearchDataRVAdapter.ViewHolder>() {

    private var searchData = listOf<SearchData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            SearchDataItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = searchData[position]
        holder.setData(data)
    }

    override fun getItemCount(): Int = searchData.size

    inner class ViewHolder(private val binding: SearchDataItemViewBinding) :
        RecyclerView.ViewHolder(binding.root){

         fun setData(data: SearchData) {
            binding.searchTextId.text = data.finedText
            val translates = data.translates
            binding.transcriptionTextId.text = translates[0].transcription
            val translateStr = StringBuilder()
            translates.forEach { translate ->
                translateStr.append(translate.translation.text).append(Constants.SEMICOLON)
            }
            binding.translateTextId.text = translateStr
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<SearchData>) {
        this.searchData = data
        notifyDataSetChanged()
    }

}
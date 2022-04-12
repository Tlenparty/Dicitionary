package com.geekbrains.dictionary.base_logic.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.dictionary.R
import com.geekbrains.dictionary.base_logic.search.SearchView
import com.geekbrains.dictionary.data.entities.SearchData
import com.geekbrains.dictionary.databinding.SearchDataItemViewBinding
import java.lang.StringBuilder

class SearchDataRVAdapter : RecyclerView.Adapter<SearchDataRVAdapter.ViewHolder>() {

    private var searchData = mutableListOf<SearchData>()
    var searchView: SearchView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            SearchDataItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
            .apply {
                //навешаем обработчик клика по списку
                this.itemView.setOnClickListener {
                    searchView?.showWordDetail(
                        this@SearchDataRVAdapter.searchData[this.currentPosition].id
                    )
                }

                //обработка избранного
                this.favorite.setOnClickListener {
                    searchView?.changeFavorite(
                        this@SearchDataRVAdapter.searchData[this.currentPosition].id,
                        this@SearchDataRVAdapter.searchData[this.currentPosition].favorite
                    )
                }
            }

    override fun getItemCount(): Int = searchData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = searchData[position]
        holder.currentPosition = position
        holder.setData { binding ->
            //задаем слово, которое нашлось для перевода
            binding.searchTextId.text = data.finedText

            //задаем список переводов
            val translates = data.translates


            val translateStr = StringBuilder()
            translates.forEach { translate ->
                translateStr.append(translate.translation.text).append("; ")
            }
            binding.translateTextId.text = translateStr

            //задаем иконку избраного
            val favoriteResourceId = if (data.favorite) {
                R.drawable.icon_favorite_on
            } else {
                R.drawable.icon_favorite_off
            }
            binding.favoriteId.setBackgroundResource(favoriteResourceId)
        }
    }

    inner class ViewHolder(private val binding: SearchDataItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        var favorite = binding.favoriteId
        var currentPosition = -1

        //задание 6
        fun setData(pros: (SearchDataItemViewBinding) -> Unit) {
            pros(binding)
        }
    }

    fun setData(data: List<SearchData>) {
        searchData = data.toMutableList()
        notifyDataSetChanged()
    }

    //обновить иконку избранного слова
    fun updateFavorite(searchDataId: Long, favorite: Boolean, deleteItem: Boolean = false) {
        var deleted = -1
        for (ind in 0 until searchData.size) {
            if (searchData[ind].id == searchDataId) {
                searchData[ind].favorite = favorite
                notifyItemChanged(ind)

                if (deleteItem) {
                    deleted = ind
                }
                break
            }
        }

        //удалим слово из списка, если нужно
        if (deleted >= 0) {
            searchData.remove(searchData[deleted])
            notifyItemRemoved(deleted)
        }
    }
}

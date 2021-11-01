package com.geekbrains.dictionary.base_logic.search.adapter

import com.geekbrains.dictionary.base_logic.BaseListPresenter
import com.geekbrains.dictionary.data.entities.SearchData

class SearchDataListPresenter: BaseListPresenter<SearchDataItemView> {

    var searchData = listOf<SearchData>()

    override fun bindView(view: SearchDataItemView) {
        val data = searchData[view.currentPosition]
        view.setData(data)
    }

    override fun getCount() = searchData.size

}
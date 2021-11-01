package com.geekbrains.dictionary.base_logic.search.adapter

import com.geekbrains.dictionary.data.entities.SearchData

interface SearchDataItemView {

    var currentPosition: Int

    //отобразить данные
    fun setData(data: SearchData)
}
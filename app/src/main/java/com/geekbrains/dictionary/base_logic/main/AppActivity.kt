package com.geekbrains.dictionary.base_logic.main

import android.os.Bundle
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geekbrains.dictionary.R
import com.geekbrains.dictionary.base_logic.BaseDiActivity
import com.geekbrains.dictionary.base_logic.search.SearchFragment
import com.geekbrains.dictionary.databinding.AppActivityBinding

class AppActivity:BaseDiActivity() {

    private lateinit var binding:AppActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AppActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SearchFragment())
                .commitNow()
        }
    }


}
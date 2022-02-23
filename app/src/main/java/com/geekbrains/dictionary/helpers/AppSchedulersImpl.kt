package com.geekbrains.dictionary.helpers

import com.geekbrains.dictionary.helpers.scheduler.AppSchedulers
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppSchedulersImpl: AppSchedulers {
    override fun background(): Scheduler = Schedulers.newThread()

    override fun main(): Scheduler = AndroidSchedulers.mainThread()

}
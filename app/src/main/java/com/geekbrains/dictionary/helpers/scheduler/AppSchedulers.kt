package com.geekbrains.dictionary.helpers.scheduler

import io.reactivex.Scheduler

interface AppSchedulers {
    fun background(): Scheduler
    fun main(): Scheduler
}
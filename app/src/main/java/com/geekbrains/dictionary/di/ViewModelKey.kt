package com.geekbrains.dictionary.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

//  Спецификаторы позволяют определить признак, по которому мы будем отличать предоставляемые экземпляры,
//  помимо типа.
// Создадим ключ, по которому будем хранить нашу ViewModel в фабрике:
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)
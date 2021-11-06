package com.geekbrains.dictionary.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
/**
Здесь есть две проблемы:
при обычном внедрении ViewModel будет пересоздаваться при каждом повороте экрана;
по умолчанию класс ViewModelProviders, который мы использовали раньше, не поддерживает создание ViewModel с конструкторами.
Это значит, что если мы хотим создавать ViewModel через Dagger, ему нужно указать, как и что передавать в конструктор через ViewModelProvider.Factory. Это класс-фабрика, который и создает ViewModel. То есть нужно создать фабрику ViewModel и внедрить её в Activity. Фабрика будет получать из моделей map, в котором она будет хранить модели или создавать новые, если их в нём нет.
*/

// @Singleton - будет создаваться единый(единственный) экземпляр зависимости для внедрения
// Проще говоря, для каждого экземпляра компонента в Scope будет только один экземпляр объекта.
@Singleton
class ViewModelFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val creator = viewModels[modelClass]
            ?: viewModels.asIterable().firstOrNull { modelClass.isAssignableFrom(it.key) }?.value
            ?: throw IllegalArgumentException("unknown model class $modelClass")
        return try {
            creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
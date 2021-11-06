package com.geekbrains.dictionary.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.geekbrains.dictionary.data.interactors.SearchInteractor
import com.geekbrains.dictionary.data.interactors.SearchInteractorImpl
import com.geekbrains.dictionary.di.ViewModelFactory
import com.geekbrains.dictionary.di.ViewModelKey
import com.geekbrains.dictionary.viewmodel.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**Особенности @Binds:
Применятся над абстрактным методом или над методом интерфейса модуля.
Возвращаемый тип метода — это интерфейс, на который мы байндим реализацию.
В качестве параметра метода указывается не зависимости, а тип конкретной реализации.
Можно применять к методу @Qualifier/@Named.
Можно указывать scope.*/

// Модуль послужит источником коллекции ViewModel’ей для фабрики:
// - мы используем этот модуль для создания ViewModel
// - мы предоставляем ключ для каждой новой ViewModel при помощи класса
// ViewModelKey;
// - и уже в Activity мы используем фабрику для создания нужной нам ViewModel

@Module
abstract class ViewModelModule {
    // @Binds Для того чтобы забайндить реализацию к интерфейсу
    // Dagger 2 позволяет нам предоставлять зависимости без наличия @Provides методов.
    // Это достигается путем наличия @Inject над конструктором у класса, который нам необходимо создать.
    @Binds
    abstract fun bindSearchInteractor(searchInteractor: SearchInteractorImpl): SearchInteractor

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    // Этот метод просто говорит Dagger’у: помести эту модель в список (map) моделей, используя
    // аннотацию @IntoMap, где в качестве ключа будет класс MainViewModel::class
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    protected abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel
}
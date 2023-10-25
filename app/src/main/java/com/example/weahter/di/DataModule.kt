package com.example.weahter.di

import com.example.weahter.remote.ApiInterface
import com.example.weahter.remote.RetrofitBuilder
import com.example.weahter.repository.Repository
import com.example.weahter.repository.RepositoryImple
import com.example.weahter.ui.Today.TodayViewmodel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single<ApiInterface> { RetrofitBuilder.apiInterfaceBuilder() }

    factory <Repository> {
        RepositoryImple(apiInterface = get())
    }
    viewModel<TodayViewmodel> {
        TodayViewmodel(repository = get())
    }
}



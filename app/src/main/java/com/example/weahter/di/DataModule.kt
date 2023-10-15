package com.example.weahter.di

import androidx.lifecycle.ViewModel
import com.example.weahter.remote.ApiInterface
import com.example.weahter.remote.RetrofitBuilder
import com.example.weahter.repository.Repository
import com.example.weahter.repository.RepositoryImple
import com.example.weahter.ui.main.MainViewmodel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.ParametersHolder
import org.koin.dsl.module

val dataModule = module {
    single<ApiInterface> { RetrofitBuilder.apiInterfaceBuilder() }

    factory <Repository> {
        RepositoryImple(apiInterface = get())
    }
    viewModel<MainViewmodel> {
        MainViewmodel(repository = get())
    }
}



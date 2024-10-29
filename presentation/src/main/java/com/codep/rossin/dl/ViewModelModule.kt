package com.codep.rossin.dl

import com.codep.rossin.ui.feature.home.HomeViewModule
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module{
    viewModel{
        HomeViewModule(get())
    }

}
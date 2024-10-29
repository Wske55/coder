package com.codep.rossin.dl

import org.koin.dsl.module

val presentationModule = module {
    includes(viewModelModule)

}
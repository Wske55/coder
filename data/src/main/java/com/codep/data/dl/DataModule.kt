package com.codep.data.dl

import org.koin.dsl.module

val dataModule = module {
    includes(networkModule, repositoryModule)

}


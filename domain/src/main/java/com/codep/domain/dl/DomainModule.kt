package com.codep.domain.dl

import org.koin.dsl.module

val domainModule = module {
    includes(useCaseModule)
}
package devlcc.io.tunedglobal.code.exam.domain.di

import devlcc.io.tunedglobal.code.exam.domain.usecase.GetTrendingAlbumsUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {

    fun build(): Module = module {

        single { GetTrendingAlbumsUseCase(albumsRepository = get()) }

    }

}
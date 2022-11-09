package devlcc.io.tunedglobal.code.exam.data.di

import devlcc.io.tunedglobal.code.exam.data.repository.AlbumsRepository
import devlcc.io.tunedglobal.code.exam.data.repository.impl.AlbumsRepositoryImpl
import devlcc.io.tunedglobal.code.exam.network.di.NetworkModule
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DataModule {

    fun build(): Module = module {

        single<AlbumsRepository> {
            AlbumsRepositoryImpl(
                networkDatasource = get(),
            )
        }

        loadKoinModules(
            listOf(
                NetworkModule.build()
            )
        )
    }

}
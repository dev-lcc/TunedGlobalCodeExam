package devlcc.io.tunedglobal.code.exam.di

import devlcc.io.tunedglobal.code.exam.feature.trendingalbumlist.TrendingAlbumListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object ViewModelModules {

    fun build(): Module = module {
        viewModel {
            TrendingAlbumListViewModel(
                getTrendingAlbumsUseCase = get(),
            )
        }
    }

}
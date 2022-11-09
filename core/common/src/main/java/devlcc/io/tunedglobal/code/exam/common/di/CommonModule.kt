package devlcc.io.tunedglobal.code.exam.common.di

import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.dsl.module

object CommonModule {

    fun build(): Module = module {
        single { provideJson() }
    }

    private fun provideJson(): Json =
        Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        }

}
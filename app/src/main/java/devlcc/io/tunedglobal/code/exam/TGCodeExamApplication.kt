package devlcc.io.tunedglobal.code.exam

import android.app.Application
import devlcc.io.tunedglobal.code.exam.common.di.CommonModule
import devlcc.io.tunedglobal.code.exam.data.di.DataModule
import devlcc.io.tunedglobal.code.exam.domain.di.DomainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TGCodeExamApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TGCodeExamApplication.applicationContext)

            modules(
                DomainModule.build(),
                DataModule.build(),
                CommonModule.build(),
            )
        }

    }

}
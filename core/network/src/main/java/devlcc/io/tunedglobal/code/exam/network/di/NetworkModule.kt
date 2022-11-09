package devlcc.io.tunedglobal.code.exam.network.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import devlcc.io.tunedglobal.code.exam.network.AlbumsNetworkService
import devlcc.io.tunedglobal.code.exam.network.BuildConfig
import devlcc.io.tunedglobal.code.exam.network.retrofit.AlbumsNetworkServiceImpl
import devlcc.io.tunedglobal.code.exam.network.retrofit.service.AlbumsRetrofitService
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create
import java.util.concurrent.TimeUnit

object NetworkModule {

    private const val apiUrlEndpoint: String = BuildConfig.API_URL_ENDPOINT
    private const val storeId: String =
        BuildConfig.TUNEDGLOBAL_STORE_ID    // REST API Header["StoreId"] token
    private val isDebug: Boolean = BuildConfig.DEBUG

    fun build(): Module = module {

        single<Cache> { provideCache(context = get()) }

        single<OkHttpClient> {
            provideOkHttpClient(
                cache = get(), storeId = storeId, isDebug = isDebug
            )
        }

        single<Retrofit> {
            provideRetrofit(
                urlEndpoint = apiUrlEndpoint,
                client = get(),
                json = get(),
            )
        }

        single<AlbumsRetrofitService> { provideRetrofitService(retrofit = get()) }
        // TODO:: Put other Retrofit Service Definitions here...

        single<AlbumsNetworkService> {
            AlbumsNetworkServiceImpl(service = get())
        }
        // TODO:: Put other Network Service Implementation Definitions here...
    }

    private fun provideCache(context: Context): Cache = Cache(context.cacheDir, 1000 * 60 * 60)

    private fun provideOkHttpClient(
        cache: Cache,
        storeId: String,
        isDebug: Boolean,
    ): OkHttpClient = OkHttpClient.Builder().cache(cache).addNetworkInterceptor { chain ->
        chain.proceed(chain.request().newBuilder().apply {
            addHeader("StoreId", storeId)
        }.build())
    }.apply {
        if (isDebug) {
            addNetworkInterceptor(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            )
        }
    }.connectTimeout(20, TimeUnit.SECONDS).readTimeout(20, TimeUnit.SECONDS).build()

    private fun provideRetrofit(
        urlEndpoint: String, client: OkHttpClient, json: Json
    ): Retrofit = Retrofit.Builder().baseUrl(urlEndpoint).addConverterFactory(
        json.asConverterFactory("application/json".toMediaType())
    ).client(client).build()

    private inline fun <reified T> provideRetrofitService(retrofit: Retrofit): T = retrofit.create()

}
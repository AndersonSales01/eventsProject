package com.example.anderson.eventsproject.di


import com.example.anderson.eventsproject.data.network.ConstantNetWork
import com.example.anderson.eventsproject.data.network.EventApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Module
  class NetWorkModule {

    @Provides
     fun EventEndPoint(retroFit: Retrofit): EventApi {
        return retroFit.create<EventApi>(EventApi::class.java)
    }

    @Provides
    fun providerRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ConstantNetWork.BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun providerOkHttpCleint(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }


//    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
//        return Retrofit.Builder().baseUrl(Constants.BASEURL).client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create()).build()
//    }

//    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
//        return OkHttpClient().newBuilder()
//            .readTimeout(60, TimeUnit.SECONDS)
//            .connectTimeout(60, TimeUnit.SECONDS)
//            .addInterceptor(authInterceptor).build()
//    }

//    fun provideApiService(retrofit: Retrofit): ApiService =
//        retrofit.create(ApiService::class.java)
}
package com.hfad.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hfad.api.ServiceGenarator
import com.hfad.usecase.IUseCase
import com.hfad.usecase.TweetsUseCase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesUseCase():IUseCase{
return TweetsUseCase(ServiceGenarator())
    }


    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideCoroutineContext(): CoroutineContext {
        return Dispatchers.Main
    }
}
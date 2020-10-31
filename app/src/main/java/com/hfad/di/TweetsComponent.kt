package com.hfad.di

import android.app.Application
import com.hfad.TweetsApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class,AppModule::class,ActivityModuleBuilder::class,
ViewModelBuilder::class])
interface TweetsComponent :AndroidInjector<TweetsApp>{

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder


        fun build(): TweetsComponent
    }
    override fun inject(app: TweetsApp)
}
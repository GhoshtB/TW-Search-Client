package com.hfad.di

import com.hfad.sliceandroidapplication.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModuleBuilder {

    @ContributesAndroidInjector
abstract fun providesActivity():MainActivity
}
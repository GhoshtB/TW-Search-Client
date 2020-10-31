package com.hfad.di

import androidx.lifecycle.ViewModel
import com.hfad.viewmodel.TweetsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(TweetsViewModel::class)
   abstract fun tweetsModelProvider(tweetsViewModel: TweetsViewModel): ViewModel
}
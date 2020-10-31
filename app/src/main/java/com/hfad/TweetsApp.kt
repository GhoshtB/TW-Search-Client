package com.hfad

import android.app.Activity
import androidx.multidex.MultiDexApplication
import com.hfad.di.DaggerTweetsComponent
import com.hfad.di.TweetsComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class TweetsApp:MultiDexApplication(),HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>



    override fun onCreate() {
        super.onCreate()
DaggerTweetsComponent.builder().application(this).build().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return  dispatchingAndroidInjector
    }
}
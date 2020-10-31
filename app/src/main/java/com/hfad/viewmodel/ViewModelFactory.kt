package com.hfad.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

@Singleton
class ViewModelFactory @Inject constructor(val creators:Map<@JvmSuppressWildcards Class<out ViewModel>,@JvmSuppressWildcards Provider<ViewModel>>):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val model= creators[modelClass]?:creators.entries.firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value?:throw IllegalArgumentException("")

        return model.get() as T
    }
}
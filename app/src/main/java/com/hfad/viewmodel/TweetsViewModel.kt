package com.hfad.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.model.Datas
import com.hfad.usecase.TweetsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class TweetsViewModel @Inject constructor(val useCase: TweetsUseCase):ViewModel() {

    val liveData:MutableLiveData<Datas> by lazy { MutableLiveData<Datas>() }
    val errorData:MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun getTweets(){
        useCase.getAllTweets({
            viewModelScope.launch {
                liveData.value = this@getAllTweets
            }
        },{error->
            viewModelScope.launch {
                errorData.value = error
            }
        })
    }
}
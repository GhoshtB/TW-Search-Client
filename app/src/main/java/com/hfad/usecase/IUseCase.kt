package com.hfad.usecase

import com.hfad.model.Datas

interface IUseCase {

    fun getAllTweets(callBack: Datas.()->Unit,error:(String)->Unit)
}
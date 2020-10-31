package com.hfad.usecase

import com.hfad.api.ApiServices
import com.hfad.api.ServiceGenarator
import com.hfad.model.Datas
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class TweetsUseCase @Inject constructor(val serviceGenarator: ServiceGenarator) :IUseCase{
    override fun getAllTweets(callBack:Datas.()->Unit,error:(String)->Unit) {

        GlobalScope.launch {
            val resonse=serviceGenarator.createNewService(ServiceGenarator.baseUrl,ApiServices::class.java).getTweets().execute()

            if (resonse.isSuccessful){
                callBack(resonse.body()!!)
            }else{
                error(resonse.message())
            }
        }
    }
}
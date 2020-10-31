package com.hfad.api

import androidx.multidex.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.time.toDuration

class ServiceGenarator @Inject constructor(){

    private val TIMEOUT_CONNECT = 30   //In seconds
    private val TIMEOUT_READ = 30   //In seconds
    private val CONTENT_TYPE = "Content-Type"
    private val CONTENT_TYPE_VALUE = "application/json"

    private val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    private var retrofit: Retrofit? = null
  companion object {
      val baseUrl:String= "https://6f8a2fec-1605-4dc7-a081-a8521fad389a.mock.pstmn.io"}

private var headerInterceptor = Interceptor{
    chain ->
    var origin=chain.request()

    origin.newBuilder().addHeader(CONTENT_TYPE,CONTENT_TYPE_VALUE)
        .method(origin.method(),origin.body())

    chain.proceed(origin)
}

    private val logger: HttpLoggingInterceptor
        get() {
            val loggingInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                loggingInterceptor.apply { loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS }.level = HttpLoggingInterceptor.Level.BODY
            }
            return loggingInterceptor
        }

    init {
        okHttpBuilder.addInterceptor(headerInterceptor)
        okHttpBuilder.addInterceptor(logger)
       okHttpBuilder.readTimeout(TIMEOUT_READ.toLong(),TimeUnit.SECONDS)
        okHttpBuilder.connectTimeout(TIMEOUT_CONNECT.toLong(),TimeUnit.SECONDS)
    }

    fun <S> createNewService(baseUrl:String,classes:Class<S>):S{
        retrofit= Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpBuilder.build())
            .addConverterFactory(GsonConverterFactory.create()).build()

        return retrofit!!.create(classes)
    }
}
package com.smallpdf.testapp.api.rest

import com.smallpdf.testapp.AppContext
import com.smallpdf.testapp.BuildConfig
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit

object ServiceGenerator {

    private val okHttpClient: OkHttpClient
    private val jacksonConverterFactory =
        JacksonConverterFactory.create(AppContext.instance?.jacksonObjectMapper)
    private val retrofit: Retrofit

    init {
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.connectTimeout(15000, TimeUnit.MILLISECONDS)
        httpClientBuilder.readTimeout(15000, TimeUnit.MILLISECONDS)
        httpClientBuilder.writeTimeout(15000, TimeUnit.MILLISECONDS)
        // Token and Retry interceptor
        httpClientBuilder.addInterceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()

            requestBuilder
                .method(original.method(), original.body())


            val request = requestBuilder.build()

            chain.proceed(request)
        }

        // HTTP logging interceptor
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        httpClientBuilder.addInterceptor(loggingInterceptor)

        okHttpClient = httpClientBuilder.build()

        retrofit = Retrofit.Builder()
            .addConverterFactory(jacksonConverterFactory)
            .addCallAdapterFactory(ObserveOnMainCallAdapterFactory(AndroidSchedulers.mainThread()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .baseUrl(API.BASE_API_URL)
            .client(okHttpClient)
            .build()
    }

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }

    internal class ObserveOnMainCallAdapterFactory(val scheduler: Scheduler) :
        CallAdapter.Factory() {

        override fun get(
            returnType: Type,
            annotations: Array<Annotation>,
            retrofit: Retrofit
        ): CallAdapter<*, *>? {
            if (CallAdapter.Factory.getRawType(returnType) != Single::class.java) {
                return null // Ignore non-Single types.
            }

            // Look up the next call adapter which would otherwise be used if this one was not present.

            val delegate = retrofit.nextCallAdapter(
                this, returnType,
                annotations
            ) as CallAdapter<Any, Single<*>>

            return object : CallAdapter<Any, Any> {
                override fun adapt(call: Call<Any>): Any {
                    // Delegate to get the normal Single...
                    val o = delegate.adapt(call)
                    // ...and change it to send notifications to the observer on the specified scheduler.
                    return o.observeOn(scheduler)
                }

                override fun responseType(): Type {
                    return delegate.responseType()
                }
            }
        }
    }
}

package com.xoxoer.gitpocket.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.xoxoer.gitpocket.BuildConfig
import com.xoxoer.gitpocket.Constants
import com.xoxoer.gitpocket.R
import com.xoxoer.lifemarklibrary.Lifemark
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object AppModule {

    @Singleton
    @Provides
    @JvmStatic
    fun providesLifemark(application: Application): Lifemark {
        return Lifemark(application)
    }

    @Singleton
    @Provides
    @JvmStatic
    fun providesRetrofitInstance(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(OkHttpClient.Builder().addInterceptor(loggingInterceptor).build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @JvmStatic
    fun providesRequestOption(): RequestOptions {
        return RequestOptions
            .placeholderOf(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
    }

    @Singleton
    @Provides
    @JvmStatic
    fun providesGlideInstance(
        application: Application,
        requestOptions: RequestOptions
    ): RequestManager {
        return Glide.with(application)
            .setDefaultRequestOptions(requestOptions)
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideAppDrawable(
        application: Application
    ): Drawable {
        return ContextCompat.getDrawable(application, R.drawable.ic_octocat_icon)!!
    }

}
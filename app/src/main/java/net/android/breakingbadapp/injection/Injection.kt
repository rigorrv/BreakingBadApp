package net.android.breakingbadapp.injection

import com.google.gson.GsonBuilder
import net.android.breakingbadapp.networking.Api
import net.android.breakingbadapp.viewmodel.MyViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var apiModule = module {

    fun apiProvider(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    fun retrofitProvider(): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder()
            .baseUrl("https://www.breakingbadapi.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    single { apiProvider(get()) }
    single { retrofitProvider() }
}

var viewModelModule = module {

    viewModel {
        MyViewModel(get())
    }
}
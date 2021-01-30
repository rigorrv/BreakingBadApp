package net.android.breakingbadapp

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import io.mockk.every
import io.mockk.mockk
import net.android.breakingbadapp.injection.MyApp
import net.android.breakingbadapp.model.home.HomeJson
import net.android.breakingbadapp.viewmodel.MyViewModel
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module


class Testing {
    private lateinit var loginViewModel: MyViewModel
    private lateinit var loginRequest: MutableLiveData<HomeJson>

    private lateinit var module: Module

    @Before
    fun before() {
        loginViewModel = mockk(relaxed = true)

        loginRequest = MutableLiveData()
        every { loginViewModel.getrLiveData() } returns loginRequest

        module = module(createdAtStart = true, override = true) {
            single { loginViewModel }
            single { mockk<MyViewModel>(relaxed = true) }
        }

        loadKoinModules(module)
    }

    @Test
    fun successLoginShowsMainVm() {
        loginRequest.postValue(null)
    }
}
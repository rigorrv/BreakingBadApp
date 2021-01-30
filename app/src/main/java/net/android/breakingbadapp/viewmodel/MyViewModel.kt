package net.android.breakingbadapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.android.breakingbadapp.model.home.HomeJson
import net.android.breakingbadapp.networking.Api

class MyViewModel(private val api: Api) : ViewModel() {


    var homeLiveData = MutableLiveData<HomeJson>()
    var characterLiveData = MutableLiveData<HomeJson>()

    init {
        getHomeInfo()
    }

    fun getHomeInfo() {
        GlobalScope.launch {
            api.getHomeJson().let {
                homeLiveData.postValue(it.body())
            }
        }
    }

    fun getCharacterInfo(name: String) {
        var names = "Walter"
        Log.d("TAG", "getCharacterInfo: ${name}")
        GlobalScope.launch {
            api.getCharacter(name).let {
                characterLiveData.postValue(it.body())
                Log.d("TAG", "getCharacterInfo: ${it.body()}")
            }
        }
    }

    fun getrLiveData():MutableLiveData<HomeJson>{
        return homeLiveData
    }


}
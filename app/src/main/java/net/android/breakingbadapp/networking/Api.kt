package net.android.breakingbadapp.networking

import net.android.breakingbadapp.model.characters.CharacterJson
import net.android.breakingbadapp.model.home.HomeJson
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface Api {

    @GET("api/characters")
    suspend fun getHomeJson(): Response<HomeJson>


    @GET("api/characters")
    suspend fun getCharacter(@Query("name", encoded = true) name: String): Response<HomeJson>


}

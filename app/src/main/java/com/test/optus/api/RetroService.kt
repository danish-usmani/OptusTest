package com.test.optus.api

import com.test.optus.models.AlbumData
import com.test.optus.models.UserData
import retrofit2.Call
import retrofit2.http.GET

interface RetroService {

   @GET("users")
   fun getUsers():Call<UserData>

   @GET("photos")
   fun getAlbums():Call<AlbumData>
}
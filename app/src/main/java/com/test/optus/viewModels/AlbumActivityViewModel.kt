package com.test.optus.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.optus.adapters.AlbumViewAdapter
import com.test.optus.api.RetroInstance
import com.test.optus.api.RetroService
import com.test.optus.models.AlbumData
import com.test.optus.models.AlbumItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumActivityViewModel : ViewModel() {
    var albumListData: MutableLiveData<AlbumData> = MutableLiveData()
    var albumViewAdapter : AlbumViewAdapter = AlbumViewAdapter()

    fun getAdapter(): AlbumViewAdapter {
        return albumViewAdapter
    }

    fun setAdapterData(data: ArrayList<AlbumItem>, iUserId: String) {
        albumViewAdapter.setDataList(data, iUserId)
        albumViewAdapter.notifyDataSetChanged()
    }

    fun getRecyclerListDataObserver(): MutableLiveData<AlbumData> {
        return albumListData
    }

    fun makeAPICall() {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getAlbums()
        call.enqueue(object : Callback<AlbumData> {
            override fun onFailure(call: Call<AlbumData>, t: Throwable) {
                albumListData.postValue(null)
            }

            override fun onResponse(call: Call<AlbumData>, response: Response<AlbumData>) {
                if(response.isSuccessful){
                    albumListData.postValue(response.body())
                } else {
                    albumListData.postValue(null)
                }
            }
        })
    }
}
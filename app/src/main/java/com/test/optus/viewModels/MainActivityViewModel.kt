package com.test.optus.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.optus.adapters.RecyclerViewAdapter
import com.test.optus.api.RetroInstance
import com.test.optus.api.RetroService
import com.test.optus.models.UserData
import com.test.optus.models.UserDataItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel(){
    var usersListData: MutableLiveData<UserData> = MutableLiveData()
    var recyclerViewAdapter : RecyclerViewAdapter = RecyclerViewAdapter()

    fun getAdapter(): RecyclerViewAdapter {
        return recyclerViewAdapter
    }

    fun setAdapterData(data: ArrayList<UserDataItem>) {
        recyclerViewAdapter.setDataList(data)
        recyclerViewAdapter.notifyDataSetChanged()
    }

    fun getRecyclerListDataObserver(): MutableLiveData<UserData> {
        return usersListData
    }

    fun makeAPICall() {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getUsers()
        call.enqueue(object : Callback<UserData>{
            override fun onFailure(call: Call<UserData>, t: Throwable) {
                usersListData.postValue(null)
            }

            override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                if(response.isSuccessful){
                    usersListData.postValue(response.body())
                } else {
                    usersListData.postValue(null)
                }
            }
        })
    }
}
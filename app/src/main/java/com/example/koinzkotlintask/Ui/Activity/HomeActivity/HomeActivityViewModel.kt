package com.example.koinzkotlintask.Ui.Activity.HomeActivity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cproductandroid.Network.RetrofitClient
import com.example.koinzkotlintask.Ui.Adapter.PhotoListAdapter
import com.example.koinzkotlintask.model.KoinzModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivityViewModel : ViewModel() {
    private var photoMutableLiveData: MutableLiveData<KoinzModel> = MutableLiveData()


    fun getPhotoMutableLiveData(): MutableLiveData<KoinzModel> {
        return photoMutableLiveData
    }


    @DelicateCoroutinesApi
    fun getDataFromServer() {
        GlobalScope.launch(Dispatchers.IO) {
            val call = RetrofitClient.apiInterface.getPhotos()
            call.enqueue(object : Callback<KoinzModel?> {
                override fun onResponse(call: Call<KoinzModel?>, response: Response<KoinzModel?>) {
                    Log.d("TAG", "onResponse: Entered " + response.body()!!.stat)

                    photoMutableLiveData.postValue(response.body())
                }
                override fun onFailure(call: Call<KoinzModel?>, t: Throwable) {
                }
            })
        }

    }

}
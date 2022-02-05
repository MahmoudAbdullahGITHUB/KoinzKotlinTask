package com.example.koinzkotlintask.Ui.Activity.HomeActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.koinzkotlintask.R
import com.example.koinzkotlintask.Ui.Adapter.PhotoListAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cproductandroid.utiles.CheckNetworkConnection
import com.example.koinzkotlintask.databinding.ActivityHomeBinding
import com.example.koinzkotlintask.model.Photo
import kotlinx.coroutines.DelicateCoroutinesApi


class HomeActivity : AppCompatActivity() {

    lateinit var myAdapter: PhotoListAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var viewModel: HomeActivityViewModel
    private lateinit var checkNetworkConnection: CheckNetworkConnection
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.recyclerView.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        gridLayoutManager = GridLayoutManager(this,2)

        binding.recyclerView.layoutManager = linearLayoutManager
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )



        callNetworkConnection()


    }


    @DelicateCoroutinesApi
    private fun getPhotosFromServer() {

        viewModel.getDataFromServer()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[HomeActivityViewModel::class.java]

        viewModel.getPhotoMutableLiveData().observe(this, Observer {
            if (it != null) {
                myAdapter = PhotoListAdapter(
                    baseContext,
                    it.photos.photo
                )

                for (pro in it.photos.photo) {
                    insertProductInDB2(pro)
                }

                myAdapter.notifyDataSetChanged()

                binding.recyclerView.adapter = myAdapter

                print("how to say")
            } else {
                println("unfroutnly ")

            }
        })

    }


    private fun callNetworkConnection() {
        checkNetworkConnection = CheckNetworkConnection(application)
        checkNetworkConnection.observe(this, { isConnected ->
            if (isConnected) {
                initViewModel()
                getPhotosFromServer()
                Log.d("TAG", "callNetworkConnection: doing well ")
            } else {
//                getProductsFromDB()
                Log.d("TAG", "callNetworkConnection: doing wrong ")

            }
        })

    }

    private fun insertProductInDB2(pro: Photo) {

    }

}


/*
    mvc
val call = RetrofitClient.apiInterface.getPhotos()

        call.enqueue(object : Callback<KoinzModel?> {
            override fun onResponse(call: Call<KoinzModel?>, response: Response<KoinzModel?>) {
                Log.d("TAG", "onResponse: Entered "+response.body()!!.stat)
                myAdapter = PhotoListAdapter(baseContext, response.body()!!.photos.photo)

                recyclerView.adapter = myAdapter
            }

            override fun onFailure(call: Call<KoinzModel?>, t: Throwable) {

            }
        })


 */
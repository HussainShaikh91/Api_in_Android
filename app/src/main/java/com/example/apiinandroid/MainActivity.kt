package com.example.apiinandroid

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apiinandroid.Adapters.DataAdapter
import com.example.apiinandroid.Api.ApiServices
import com.example.apiinandroid.databinding.ActivityMainBinding
import com.example.apiinandroid.models.DataModelClassItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    var dialog: ProgressDialog? = null

    lateinit var adapter: DataAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        dialog = ProgressDialog(this)
        dialog!!.setMessage("Loading...")
        dialog!!.setCancelable(false)
        dialog!!.show()

        //Calling function
        fetchData()

    }

    //function for Fetch data from api
    private fun fetchData(){
        val retrofit = Retrofit.Builder() //Create Retrofit object
            .baseUrl(BASE_URL) //Here pass Base Url
            .addConverterFactory(GsonConverterFactory.create()) //for convert Gson
            .build()
            .create(ApiServices::class.java)
        val retrofitData = retrofit.getRequest()

        //for generate automatically function shortcut key CTRL + Shift + Space
        retrofitData.enqueue(object : Callback<List<DataModelClassItem>?> {
            override fun onResponse(call: Call<List<DataModelClassItem>?>, response: Response<List<DataModelClassItem>?>) {
                //Get Response from api
                val responseBody = response.body()!!
                //Hide Dialog
                dialog!!.dismiss()
                //Set RecyclerView
                binding!!.rvView.setHasFixedSize(true) //set recyclerView size fix
                linearLayoutManager = LinearLayoutManager(this@MainActivity) //set layout manager
                binding!!.rvView.layoutManager = linearLayoutManager

                //set data in to adapter
                adapter = DataAdapter(this@MainActivity,responseBody)
                adapter.notifyDataSetChanged() //notify adapter
                binding!!.rvView.adapter = adapter

            }

            override fun onFailure(call: Call<List<DataModelClassItem>?>, t: Throwable) {
                Log.e("MainActivity", "onFailure: " + t.message)
            }
        })
    }

    companion object{
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }


}
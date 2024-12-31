package com.example.getapi

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.getapi.adapter.RecyclerAdapter
import com.example.getapi.adapter.RecyclerInterface
import com.example.getapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), RecyclerInterface {
    lateinit var binding: ActivityMainBinding
    lateinit var linearLayoutManager: LinearLayoutManager
    var array= arrayListOf<ResponseModel.Data>()
    var recyclerAdapter:RecyclerAdapter= RecyclerAdapter(this,array,this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        linearLayoutManager=LinearLayoutManager(this)
        binding.recyclerView.layoutManager=linearLayoutManager
        binding.recyclerView.adapter=recyclerAdapter
        get()
    }
    object RetrofitClass{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var api = retrofit.create(ApiInterface::class.java)
    }
    fun get(){
        val call=RetrofitClass.api.getData()
        call.enqueue(object:Callback<ResponseModel>{
            override fun onResponse(
                call: Call<ResponseModel>,
                response: Response<ResponseModel>
            ) {
                if (response.code()==200){
                    if (response.body()!=null){
                        array.addAll(response.body()!!.data)
                        recyclerAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Toast.makeText(this@MainActivity, "api hit nhi hui", Toast.LENGTH_SHORT).show()
            }

        }
        )
    }

    override fun delete(position: Int) {
        var alertDialog = AlertDialog.Builder(this@MainActivity)
        alertDialog.setTitle("Delete Item")
        alertDialog.setMessage("Do you want to delete the item?")
        alertDialog.setCancelable(false)
        alertDialog.setNegativeButton("No") { _, _ ->
            alertDialog.setCancelable(true)
        }
        alertDialog.setPositiveButton("Yes") { _, _ ->
            if (array.size == 0){
                Toast.makeText(this@MainActivity, "List Is Empty", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(
                    this@MainActivity,
                    "The item is deleted",
                    Toast.LENGTH_SHORT
                ).show()
                array.removeAt(position)
                recyclerAdapter.notifyDataSetChanged()
            }
        }
        alertDialog.show()
    }

    override fun itemClick(position: Int, model: ResponseModel.Data) {
        Toast.makeText(this@MainActivity, "item clicked", Toast.LENGTH_SHORT).show()
        val intent=Intent(this@MainActivity,MainActivity2::class.java)
        intent.putExtra("first_name",model.first_name)
        intent.putExtra("last_name",model.last_name)
        intent.putExtra("email",model.email)
        startActivity(intent)
    }
}
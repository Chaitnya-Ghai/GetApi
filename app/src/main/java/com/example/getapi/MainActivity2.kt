package com.example.getapi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.getapi.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    val binding: ActivityMain2Binding by lazy {
        ActivityMain2Binding.inflate(layoutInflater)
    }
    var first_name:String?=null
    var last_name:String?=null
    var email:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        first_name=intent.getStringExtra("first_name")
        last_name=intent.getStringExtra("last_name")
        email=intent.getStringExtra("email")

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.text1.text=first_name
        binding.text2.text=last_name
        binding.text3.text=email
    }
}
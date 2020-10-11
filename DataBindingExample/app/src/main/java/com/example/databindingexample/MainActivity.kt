package com.example.databindingexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.databindingexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.nameInput.setOnClickListener { _: View? -> displayName() }
    }
    private  fun displayName(){
        binding.apply {
            var name = nameInput.text
            if(name.isBlank()){
                Toast.makeText(applicationContext, "Please enter your name",Toast.LENGTH_LONG).show()
            }
            else{
                nameInput.setText("Hello ${name} !")
            }
        }
    }
}

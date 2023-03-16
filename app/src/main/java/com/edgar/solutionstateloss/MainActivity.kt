package com.edgar.solutionstateloss

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.edgar.solutionstateloss.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnException.setOnClickListener {
            startActivity(Intent(this, StateLossActivity::class.java))
        }

        binding.btnSolution.setOnClickListener {
            startActivity(Intent(this, CustomSolutionActivity::class.java))
        }
    }
}
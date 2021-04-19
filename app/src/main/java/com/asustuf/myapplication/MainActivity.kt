package com.asustuf.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asustuf.myapplication.databinding.ActivityMainBinding
import com.asustuf.myapplication.samples.SamplesAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.samplesRv.layoutManager = LinearLayoutManager(this)
        mainBinding.samplesRv.adapter = SamplesAdapter(this, 5)
    }
}
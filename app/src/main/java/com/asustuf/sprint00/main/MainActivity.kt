package com.asustuf.sprint00.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.asustuf.sprint00.databinding.ActivityMainBinding
import com.asustuf.sprint00.main.adapters.SamplesAdapter
import com.asustuf.sprint00.task1.Task1Activity
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        val adapter = SamplesAdapter(this)

        mainBinding.samplesRv.layoutManager = LinearLayoutManager(this)
        mainBinding.samplesRv.adapter = adapter

        mainBinding.addSampleBtn.setOnClickListener {
            if (adapter.itemCount < 26) {
                adapter.addSample()
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Maximum count of samples is 26", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        mainBinding.removeSampleBtn.setOnClickListener {
            if (adapter.itemCount > 2) {
                adapter.removeSample()
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Minimum count of samples is 2", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        mainBinding.defaultSamplesBtn.setOnClickListener {
            adapter.setDefaultSamples()
            adapter.notifyDataSetChanged()
        }

        mainBinding.cleanSamplesBtn.setOnClickListener {
            adapter.cleanSamples()
            adapter.notifyDataSetChanged()
        }

        mainBinding.task1Btn.setOnClickListener {
            val sampleA = adapter.getSample('A')
            val sampleB = adapter.getSample('B')

            val samples = mutableListOf<MutableList<Double>>()
            samples.add(sampleA)
            samples.add(sampleB)

            val intent = Intent(this, Task1Activity::class.java)
            intent.putExtra("samples", samples as Serializable)
            startActivity(intent)
            //TODO add to recycler checkboxes and push in it a lot of samples (not only A and B)
        }

        mainBinding.task2Btn.setOnClickListener {
            //TODO do task
        }

        mainBinding.task3Btn.setOnClickListener {
            //TODO do task
        }

        mainBinding.task4Btn.setOnClickListener {
            //TODO do task
        }

        mainBinding.task5Btn.setOnClickListener {
            //TODO do task
        }

    }
}
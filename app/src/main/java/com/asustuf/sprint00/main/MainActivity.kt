package com.asustuf.sprint00.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asustuf.sprint00.databinding.ActivityMainBinding
import com.asustuf.sprint00.main.adapters.SamplesAdapter
import com.asustuf.sprint00.task1.Task1Activity
import com.asustuf.sprint00.task2.Task2Activity
import com.asustuf.sprint00.task3.Task3Activity
import com.asustuf.sprint00.task4.Task4Activity
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
            val checkedSamples = adapter.getCheckedSamples()

            if (checkedSamples.isEmpty()) {
                Toast.makeText(this, "Samples are empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, Task1Activity::class.java)
            intent.putExtra("samples", checkedSamples as Serializable)
            startActivity(intent)
        }

        mainBinding.task2Btn.setOnClickListener {
            val checkedSamples = adapter.getCheckedSamples()

            if (checkedSamples.isEmpty()) {
                Toast.makeText(this, "Samples are empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, Task2Activity::class.java)
            intent.putExtra("samples", checkedSamples as Serializable)
            startActivity(intent)
        }

        mainBinding.task3Btn.setOnClickListener {
            val checkedSamples = adapter.getCheckedSamples()

            if (checkedSamples.isEmpty()) {
                Toast.makeText(this, "Samples are empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, Task3Activity::class.java)
            intent.putExtra("samples", checkedSamples as Serializable)
            startActivity(intent)
        }

        mainBinding.task4Btn.setOnClickListener {
            val checkedSamples = adapter.getCheckedSamples()

            if (checkedSamples.isEmpty()) {
                Toast.makeText(this, "Samples are empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, Task4Activity::class.java)
            intent.putExtra("samples", checkedSamples as Serializable)
            startActivity(intent)
        }

        mainBinding.task5Btn.setOnClickListener {
            //TODO do task
        }

    }
}
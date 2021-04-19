package com.asustuf.sprint00

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.asustuf.sprint00.databinding.ActivityMainBinding
import com.asustuf.sprint00.samples.SamplesAdapter

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

    }
}
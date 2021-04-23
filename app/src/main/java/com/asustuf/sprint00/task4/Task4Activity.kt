package com.asustuf.sprint00.task4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.asustuf.sprint00.databinding.ActivityTask3Binding
import com.asustuf.sprint00.model.dataclasses.Sample
import com.asustuf.sprint00.task4.adapters.Task4SampleAdapter

class Task4Activity : AppCompatActivity() {
    private lateinit var task4Binding: ActivityTask3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        task4Binding = ActivityTask3Binding.inflate(layoutInflater)
        setContentView(task4Binding.root)

        @Suppress("UNCHECKED_CAST")
        val samples = intent.getSerializableExtra("samples") as MutableList<Sample>

        task4Binding.task3ForSampleRv.layoutManager = LinearLayoutManager(this)
        task4Binding.task3ForSampleRv.adapter = Task4SampleAdapter(samples)
    }
}
package com.asustuf.sprint00.task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.asustuf.sprint00.databinding.ActivityTask2Binding
import com.asustuf.sprint00.model.dataclasses.Sample
import com.asustuf.sprint00.task2.adapters.Task2SampleAdapter

class Task2Activity : AppCompatActivity() {
    private lateinit var task2Binding: ActivityTask2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        task2Binding = ActivityTask2Binding.inflate(layoutInflater)
        setContentView(task2Binding.root)

        @Suppress("UNCHECKED_CAST")
        val samples = intent.getSerializableExtra("samples") as MutableList<Sample>

        task2Binding.task2ForSampleRv.layoutManager = LinearLayoutManager(this)
        task2Binding.task2ForSampleRv.adapter = Task2SampleAdapter(samples)
    }
}
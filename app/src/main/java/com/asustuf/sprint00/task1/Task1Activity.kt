package com.asustuf.sprint00.task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.asustuf.sprint00.databinding.ActivityTask1Binding
import com.asustuf.sprint00.task1.adapters.Task1SampleAdapter
import com.asustuf.sprint00.dataclasses.Sample

class Task1Activity : AppCompatActivity() {
    lateinit var task1Binding: ActivityTask1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        task1Binding = ActivityTask1Binding.inflate(layoutInflater)
        setContentView(task1Binding.root)

        val samples = intent.getSerializableExtra("samples") as MutableList<Sample>

        task1Binding.task1ForSample.layoutManager = LinearLayoutManager(this)
        task1Binding.task1ForSample.adapter = Task1SampleAdapter(samples)

    }
}
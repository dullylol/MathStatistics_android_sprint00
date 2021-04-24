package com.asustuf.sprint00.task5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.asustuf.sprint00.task5.adapters.Task5SampleAdapter
import com.asustuf.sprint00.databinding.ActivityTask5Binding
import com.asustuf.sprint00.model.dataclasses.Sample

class Task5Activity : AppCompatActivity() {
    private lateinit var task5Binding: ActivityTask5Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        task5Binding = ActivityTask5Binding.inflate(layoutInflater)
        setContentView(task5Binding.root)

        @Suppress("UNCHECKED_CAST")
        val samples = intent.getSerializableExtra("samples") as MutableList<Sample>

        task5Binding.task5ForSampleRv.layoutManager = LinearLayoutManager(this)
        task5Binding.task5ForSampleRv.adapter = Task5SampleAdapter(samples)
    }
}
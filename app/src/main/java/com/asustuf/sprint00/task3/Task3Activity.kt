package com.asustuf.sprint00.task3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.asustuf.sprint00.databinding.ActivityTask3Binding
import com.asustuf.sprint00.model.dataclasses.Sample
import com.asustuf.sprint00.task3.adapter.Task3SampleAdapter

class Task3Activity : AppCompatActivity() {
    lateinit var task3Binding: ActivityTask3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        task3Binding = ActivityTask3Binding.inflate(layoutInflater)
        setContentView(task3Binding.root)

        @Suppress("UNCHECKED_CAST")
        val samples = intent.getSerializableExtra("samples") as MutableList<Sample>

        task3Binding.task3ForSampleRv.layoutManager = LinearLayoutManager(this)
        task3Binding.task3ForSampleRv.adapter = Task3SampleAdapter(samples)
    }
}
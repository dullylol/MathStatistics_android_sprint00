package com.asustuf.sprint00.task5.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asustuf.sprint00.databinding.Task5ForSampleRvBinding
import com.asustuf.sprint00.model.dataclasses.Sample
import com.asustuf.sprint00.model.SampleOperations

class Task5SampleAdapter(private val samples: MutableList<Sample>) :
    RecyclerView.Adapter<Task5SampleAdapter.Task5SampleHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Task5SampleHolder {
        val binding =
            Task5ForSampleRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Task5SampleHolder(binding)
    }

    override fun onBindViewHolder(holder: Task5SampleHolder, position: Int) {
        holder.bind(samples[position])
    }

    override fun getItemCount(): Int {
        return samples.size
    }

    class Task5SampleHolder(private val binding: Task5ForSampleRvBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(sample: Sample) {
            val sampleOperations = SampleOperations(sample.sampleNumbers.toTypedArray())
            binding.sampleName.text = sample.sampleName
            binding.expectationIntervalEstimate.text =
                "Expectation interval estimate: ${sampleOperations.expectationIntervalEstimate}"
            binding.intervalEstimateOfStandardDeviation.text =
                "Interval estimate of standard deviation: ${sampleOperations.intervalEstimateOfStandardDeviation}"

        }
    }
}
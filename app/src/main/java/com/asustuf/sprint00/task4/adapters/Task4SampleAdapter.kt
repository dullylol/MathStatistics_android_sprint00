package com.asustuf.sprint00.task4.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asustuf.sprint00.databinding.Task4ForSampleRvBinding
import com.asustuf.sprint00.model.dataclasses.Sample
import com.asustuf.sprint00.model.SampleOperations

class Task4SampleAdapter(private val samples: MutableList<Sample>) :
    RecyclerView.Adapter<Task4SampleAdapter.Task4SampleHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Task4SampleHolder {
        val binding =
            Task4ForSampleRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Task4SampleHolder(binding)
    }

    override fun onBindViewHolder(holder: Task4SampleHolder, position: Int) {
        holder.bind(samples[position])
    }

    override fun getItemCount(): Int {
        return samples.size
    }

    class Task4SampleHolder(private val binding: Task4ForSampleRvBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(sample: Sample) {
            val sampleOperations = SampleOperations(sample.sampleNumbers.toTypedArray())
            binding.momentsPointEstimateFirst.text =
                "● Point estimate first(moments method): ${sampleOperations.momentsPointEstimateFirst}"
            binding.momentsPointEstimateSecond.text =
                "● Point estimate second(moments method): ${sampleOperations.momentsPointEstimateSecond}"
            binding.similarityPointEstimateFirst.text =
                "● Point estimate first(similarity method): ${sampleOperations.similarityPointEstimateFirst}"
            binding.similarityPointEstimateSecond.text =
                "● Point estimate second(similarity method): ${sampleOperations.similarityPointEstimateSecond}"
        }
    }
}
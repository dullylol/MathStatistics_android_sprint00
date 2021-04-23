package com.asustuf.sprint00.task1.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asustuf.sprint00.databinding.Task1ForSampleRvBinding
import com.asustuf.sprint00.model.dataclasses.Sample
import com.asustuf.sprint00.model.SampleOperations

class Task1SampleAdapter(private val samples: MutableList<Sample>) :
    RecyclerView.Adapter<Task1SampleAdapter.Task1SampleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Task1SampleViewHolder {
        val binding =
            Task1ForSampleRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Task1SampleViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: Task1SampleViewHolder, position: Int) {
        holder.bind(samples[position])
    }

    override fun getItemCount(): Int {
        return samples.size
    }

    class Task1SampleViewHolder(private val binding: Task1ForSampleRvBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(sample: Sample) {
            val sampleOperations = SampleOperations(sample.sampleNumbers.toTypedArray())

            binding.sampleName.text = sample.sampleName
            binding.variationRow.text = "Variation row: " + sampleOperations.getVariationRow()
                .toList().toString()
            binding.variationRowRv.layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            binding.variationRowRv.adapter = VariationRowAdapter(sample.sampleNumbers)

            binding.sampleSpan.text = "Sample span: " +sampleOperations.span

            // Imaging recyclers
            binding.cumulativeFrequencyRv.layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            binding.cumulativeFrequencyRv.adapter = FrequencyAdapter(sampleOperations
                .cumulativeFrequency)

            binding.relativeFrequencyRv.layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            binding.relativeFrequencyRv.adapter =
                FrequencyAdapter(sampleOperations.relativeFrequency)

            binding.cumulativeRelativeFrequencyRv.layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            binding.cumulativeRelativeFrequencyRv.adapter =
                FrequencyAdapter(sampleOperations.cumulativeRelativeFrequency)
        }
    }
}
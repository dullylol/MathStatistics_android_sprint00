package com.asustuf.sprint00.task1.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asustuf.sprint00.databinding.Task1ForSampleRvBinding
import com.asustuf.sprint00.dataclasses.Sample

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
            val sampleSize = sample.sampleNumbers.size
            val sampleSet = sample.sampleNumbers.toSortedSet()
            val numbersCounts = mutableListOf<Double>()

            sampleSet.forEach { setNumber ->
                var count = 0
                sample.sampleNumbers.forEach { sampleNumber ->
                    if (sampleNumber.equals(setNumber)) {
                        count++
                    }
                }
                numbersCounts.add(count.toDouble())
            }

            // Cumulative frequency
            val cumulativeFrequency = mutableListOf(numbersCounts[0])
            for (i in 1 until numbersCounts.size) {
                cumulativeFrequency.add(cumulativeFrequency[i - 1] + numbersCounts[i])
            }

            // Relative frequency
            val relativeFrequency = mutableListOf<Double>()
            for (i in sampleSet.indices) {
                relativeFrequency.add(numbersCounts[i] / sampleSize.toDouble())
            }

            // Cumulative relative frequency
            val cumulativeRelativeFrequency = mutableListOf<Double>()
            cumulativeFrequency.forEach {
                cumulativeRelativeFrequency.add(it / sampleSize)
            }

            // Variation row
            binding.variationRow.text = "Variation row: " + sample.sampleNumbers
                .apply{ this.sort() }.toString()

            // Sample name
            binding.sampleName.text = sample.sampleName

            // Variation sample
            binding.variationRowRv.layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            binding.variationRowRv.adapter = VariationRowAdapter(sample.sampleNumbers)

            // Span
            binding.sampleSpan.text = "Sample span: " +
                    "${(sample.sampleNumbers.maxOrNull()!! - sample.sampleNumbers.minOrNull()!!)}"

            // Imaging recyclers
            binding.cumulativeFrequencyRv.layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            binding.cumulativeFrequencyRv.adapter = FrequencyAdapter(cumulativeFrequency.toTypedArray())

            binding.relativeFrequencyRv.layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            binding.relativeFrequencyRv.adapter =
                FrequencyAdapter(relativeFrequency.toTypedArray())

            binding.cumulativeRelativeFrequencyRv.layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            binding.cumulativeRelativeFrequencyRv.adapter =
                FrequencyAdapter(cumulativeRelativeFrequency.toTypedArray())
        }
    }
}
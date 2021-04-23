package com.asustuf.sprint00.task3.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asustuf.sprint00.databinding.Task3ForSampleRvBinding
import com.asustuf.sprint00.model.dataclasses.Sample
import com.asustuf.sprint00.model.SampleOperations

class Task3SampleAdapter(private val samples: MutableList<Sample>) :
    RecyclerView.Adapter<Task3SampleAdapter.Task3SampleHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Task3SampleHolder {
        val binding =
            Task3ForSampleRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Task3SampleHolder(binding)
    }

    override fun onBindViewHolder(holder: Task3SampleHolder, position: Int) {
        holder.bind(samples[position])
    }

    override fun getItemCount(): Int {
        return samples.size
    }

    class Task3SampleHolder(private val binding: Task3ForSampleRvBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(sample: Sample) {
            val sampleOperations = SampleOperations(sample.sampleNumbers.toTypedArray())

            binding.sampleName.text = sample.sampleName
            binding.sampleAverage.text = "Sample average: ${sampleOperations.sampleAverage}"
            binding.median.text = "Median: ${sampleOperations.median}"
            binding.moda.text = "Moda: ${sampleOperations.moda}"
            binding.selectiveVariance.text =
                "Selective variance: ${sampleOperations.selectiveVariance}"
            binding.sampleStandardDeviation.text =
                "Sample standard deviation: ${sampleOperations.sampleStandardDeviation}"
            binding.coefficientOfVariation.text =
                "Coefficient of variation: ${sampleOperations.coefficientOfVariation * 100}%"
            binding.centralMoment3.text = "Central moment(3): ${sampleOperations.centralMoment3}"
            binding.centralMoment4.text = "Central moment(4): ${sampleOperations.centralMoment4}"
            binding.asymmetry.text = "Asymmetry: ${sampleOperations.asymmetry}"
            binding.excess.text = "Excess: ${sampleOperations.excess}"
            binding.correctedVariance.text =
                "Corrected variance: ${sampleOperations.correctedVariance}"
            binding.correctedStandardDeviation.text =
                "Corrected standard deviation: ${sampleOperations.correctedStandardDeviation}"
        }
    }
}
package com.asustuf.sprint00.task1.adapters

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.asustuf.sprint00.utils.Alphabet
import com.asustuf.sprint00.R
import com.asustuf.sprint00.databinding.Task1ForSampleBinding

class Task1SampleAdapter(private val samples: MutableList<MutableList<Double>>) :
    RecyclerView.Adapter<Task1SampleAdapter.Task1SampleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Task1SampleViewHolder {
        val binding =
            Task1ForSampleBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Task1SampleViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: Task1SampleViewHolder, position: Int) {
        holder.bind(samples[position], position)
    }

    override fun getItemCount(): Int {
        return samples.size
    }

    class Task1SampleViewHolder(private val binding: Task1ForSampleBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(sample: MutableList<Double>, index: Int) {
            binding.sampleName.text = Alphabet.samplesNames[index].toString()
            
            sample.sort()
            sample.toSet().forEach {
                val sampleNumber = TextView(context)
                sampleNumber.text = it.toString()
                sampleNumber.background =  ContextCompat.getDrawable(context,
                    R.drawable.table_element
                )
                sampleNumber.gravity = Gravity.CENTER
                sampleNumber.textSize = 18.0F
                sampleNumber.setPadding(8, 10, 8, 10)
                binding.sampleNumber.addView(sampleNumber)

                val numbersCount = TextView(context)
                var count = 0
                sample.forEach { number ->
                    if (number.equals(it)) {
                        count++
                    }
                }
                numbersCount.text = count.toString()
                numbersCount.background =  ContextCompat.getDrawable(context,
                    R.drawable.table_element
                )
                numbersCount.gravity = Gravity.CENTER
                numbersCount.textSize = 18.0F
                numbersCount.setPadding(6, 8, 6, 8)
                binding.numbersCount.addView(numbersCount)
            }
        }
    }
}
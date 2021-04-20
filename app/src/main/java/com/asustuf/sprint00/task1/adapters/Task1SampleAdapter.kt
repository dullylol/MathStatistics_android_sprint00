package com.asustuf.sprint00.task1.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asustuf.sprint00.utils.Alphabet
import com.asustuf.sprint00.databinding.Task1ForSampleRvBinding

class Task1SampleAdapter(private val samples: MutableList<MutableList<Double>>) :
    RecyclerView.Adapter<Task1SampleAdapter.Task1SampleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Task1SampleViewHolder {
        val binding =
            Task1ForSampleRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Task1SampleViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: Task1SampleViewHolder, position: Int) {
        holder.bind(samples[position], position)
    }

    override fun getItemCount(): Int {
        return samples.size
    }

    class Task1SampleViewHolder(private val binding: Task1ForSampleRvBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(sample: MutableList<Double>, index: Int) {
            binding.sampleName.text = Alphabet.samplesNames[index].toString()
            
            binding.variationRowRv.layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            binding.variationRowRv.adapter = VariationRowAdapter(sample)
        }
    }
}
package com.asustuf.sprint00.task1.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asustuf.sprint00.databinding.FrequencyRvBinding
import kotlin.math.round

class FrequencyAdapter(private val frequency: Array<Double>) :
    RecyclerView.Adapter<FrequencyAdapter.CumulativeFrequencyAdapter>() {

    init {
        for (i in frequency.indices) {
            frequency[i] = round(frequency[i] * 1000) / 1000
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CumulativeFrequencyAdapter {
        val binding =
           FrequencyRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CumulativeFrequencyAdapter(binding)
    }

    override fun onBindViewHolder(holder: CumulativeFrequencyAdapter, position: Int) {
        holder.bind(frequency[position])
    }

    override fun getItemCount(): Int {
        return frequency.size
    }


    class CumulativeFrequencyAdapter(private val binding: FrequencyRvBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(number: Double) {
            binding.number.text = number.toString()
        }
    }
}
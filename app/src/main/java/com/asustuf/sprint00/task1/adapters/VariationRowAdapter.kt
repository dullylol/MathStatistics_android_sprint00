package com.asustuf.sprint00.task1.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asustuf.sprint00.databinding.VariarionRowRvBinding

class VariationRowAdapter(private val sample: MutableList<Double>) :
    RecyclerView.Adapter<VariationRowAdapter.VariationRowHolder>() {

    private val sampleSet = sample.toSet()

    init {
        sample.sort()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VariationRowHolder {
        val binding =
            VariarionRowRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VariationRowHolder(binding)
    }

    override fun onBindViewHolder(holder: VariationRowHolder, position: Int) {
        holder.bind(sample, sampleSet, position)
    }

    override fun getItemCount(): Int {
        return sampleSet.size
    }

    class VariationRowHolder(private val binding: VariarionRowRvBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(sample: MutableList<Double>, sampleSet: Set<Double>, position: Int) {
            val sampleSetNumber = sampleSet.elementAt(position)
            binding.sampleNumber.text = sampleSetNumber.toString()

            var count = 0
            sample.forEach { sampleNumber ->
                if (sampleNumber.equals(sampleSetNumber)) {
                    count++
                }
            }

            binding.numbersCount.text = count.toString()

        }
    }

}
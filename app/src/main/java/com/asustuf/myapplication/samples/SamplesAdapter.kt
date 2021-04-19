package com.asustuf.myapplication.samples

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.asustuf.myapplication.databinding.SampleBinding

class SamplesAdapter(private val context: Context, samplesCount: Int = 2) :
    RecyclerView.Adapter<SamplesAdapter.SampleViewHolder>() {

    private val samplesNames = arrayOf(
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    )
    private var samples = ArrayList<EditText>(samplesNames.size)

    init {
        val newSamplesCount = if (samplesCount in 2..26) { samplesCount } else { 2 }

        for (i in 0 until newSamplesCount) {
            samples.add(EditText(context))
        }
    }

    fun addSample() {
        if (samples.size <= samplesNames.size) {
            samples.add(EditText(context))
        }
    }

    fun removeSample() {
        if (samples.size > 2) {
            samples.removeLast()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        val sampleBinding =
            SampleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SampleViewHolder(sampleBinding)
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        if (position in samplesNames.indices) {
            holder.bind(samplesNames[position])
        } else {
            holder.bind('Z')
        }
    }

    override fun getItemCount(): Int {
        return samples.size
    }

    class SampleViewHolder(private val sample: SampleBinding) :
        RecyclerView.ViewHolder(sample.root) {
        fun bind(sampleName: Char) {
            sample.sampleName.text = "$sampleName:"
        }
    }
}
package com.asustuf.sprint00.main.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.asustuf.sprint00.databinding.SampleRvBinding
import com.asustuf.sprint00.utils.Alphabet.Companion.samplesNames
import java.lang.NumberFormatException

class SamplesAdapter(private val context: Context, samplesCount: Int = 2) :
    RecyclerView.Adapter<SamplesAdapter.SampleViewHolder>() {


    private var samples = mutableListOf<EditText>()

    init {
        val newSamplesCount = if (samplesCount in 2..26) {
            samplesCount
        } else {
            2
        }

        for (i in 0 until newSamplesCount) {
            samples.add(EditText(context))
        }
    }

    fun getSample(sampleName: Char): MutableList<Double> {
        val index = samplesNames.indexOf(sampleName)
        val strSample = if (index != -1 && samples.size > index) {
            samples[index].text.toString()
        } else { "" }

        val fixedStrSample = "\\s".toRegex().split(strSample.trim())
        val sample = mutableListOf<Double>()

        if (fixedStrSample.isEmpty()) {
            return mutableListOf()
        }

        try {
            fixedStrSample.forEach {
                sample.add(it.toDouble())
            }
        } catch (e: NumberFormatException) {
            sample.add(0.0)
        }

        return sample
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

    @SuppressLint("SetTextI18n")
    fun setDefaultSamples() {
        while (samples.size > 2) {
            samples.removeLast()
        }
        samples[0].setText(
            "1 32 12 12 14 25 18 20 18 25 18 20 14 20 14 16 14 14 " +
            "18 16 18 20 22 16 18 14 18 18 18 20 20 20 22 18 32 20 16 " +
            "32 20 20 16 24 18 32 20 24 22 18 18 22 25 14 20 16 20 20"
        )
        samples[1].setText(
            "5.57 6.08 0.48 8.58 5.8 9.2 3.87 3.02 7.3 7.2 4.84 2.73 5.24 9.57 6.72 " +
            "9.67 7.8 0.44 -0.5 3.16 7.39 9.67 5.7 1.95 4.02 7.37 6.93 2.42 9.06 1.78 "
        )
    }

    fun cleanSamples() {
        for (i in samples.indices) {
            samples[i].setText("")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        val sampleBinding =
            SampleRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SampleViewHolder(sampleBinding)
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        if (position in samplesNames.indices) {
            holder.bind(samplesNames[position], samples[position])
        } else {
            holder.bind('Z', samples[position])
        }
    }

    override fun getItemCount(): Int {
        return samples.size
    }

    class SampleViewHolder(private val sample: SampleRvBinding) :
        RecyclerView.ViewHolder(sample.root) {
        fun bind(sampleName: Char, sampleNumbers: EditText) {
            sample.sampleNumbers.text = sampleNumbers.text
            sample.sampleName.text = "$sampleName:"

            sample.sampleNumbers.addTextChangedListener {
                sampleNumbers.text = it
            }
        }
    }
}
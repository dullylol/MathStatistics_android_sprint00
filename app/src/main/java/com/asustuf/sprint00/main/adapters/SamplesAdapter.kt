package com.asustuf.sprint00.main.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.asustuf.sprint00.databinding.SampleRvBinding
import com.asustuf.sprint00.utils.Alphabet.Companion.samplesNames
import com.asustuf.sprint00.utils.Sample
import com.asustuf.sprint00.utils.SampleBox
import java.lang.NumberFormatException

class SamplesAdapter(private val context: Context, samplesCount: Int = 2) :
    RecyclerView.Adapter<SamplesAdapter.SampleViewHolder>() {


    private var samples = mutableListOf<SampleBox>()

    init {
        val newSamplesCount = if (samplesCount in 2..26) {
            samplesCount
        } else {
            2
        }

        for (i in 0 until newSamplesCount) {
            samples.add(SampleBox(CheckBox(context), EditText(context)))
        }
    }

    fun getCheckedSamples(): MutableList<Sample> {
        val checkedSamples = mutableListOf<Sample>()

        for (sample in samples) {
            if (sample.nameChb.isChecked) {
                val strSample = sample.numbers.text.toString()

                if (strSample.replace(" ", "") != "") {
                    val fixedStrSample = "\\s".toRegex().split(strSample.trim())
                    val tempSample = mutableListOf<Double>()

                    try {
                        fixedStrSample.forEach {
                            tempSample.add(it.toDouble())
                        }
                    } catch (e: NumberFormatException) {
                        tempSample.add(0.0)
                    }
                    checkedSamples.add(Sample(sample.nameChb.text.toString(), tempSample))
                }
            }
        }

        return checkedSamples
    }

    fun addSample() {
        if (samples.size <= samplesNames.size) {
            samples.add(SampleBox(CheckBox(context), EditText(context)))
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
        samples[0].numbers.setText(
            "1 32 12 12 14 25 18 20 18 25 18 20 14 20 14 16 14 14 " +
            "18 16 18 20 22 16 18 14 18 18 18 20 20 20 22 18 32 20 16 " +
            "32 20 20 16 24 18 32 20 24 22 18 18 22 25 14 20 16 20 20"
        )
        samples[1].numbers.setText(
            "5.57 6.08 0.48 8.58 5.8 9.2 3.87 3.02 7.3 7.2 4.84 2.73 5.24 9.57 6.72 " +
            "9.67 7.8 0.44 -0.5 3.16 7.39 9.67 5.7 1.95 4.02 7.37 6.93 2.42 9.06 1.78 "
        )
        samples[0].nameChb.isChecked = true
        samples[1].nameChb.isChecked = true
    }

    fun cleanSamples() {
        for (i in samples.indices) {
            samples[i].numbers.setText("")
            samples[i].nameChb.isChecked = false
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        val sampleBinding =
            SampleRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SampleViewHolder(sampleBinding)
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.bind(samplesNames[position], samples[position])
    }

    override fun getItemCount(): Int {
        return samples.size
    }

    class SampleViewHolder(private val sampleBinding: SampleRvBinding) :
        RecyclerView.ViewHolder(sampleBinding.root) {
        fun bind(sampleName: Char, sampleBox: SampleBox) {
            sampleBinding.sampleNumbers.text = sampleBox.numbers.text
            sampleBinding.sampleNameChb.text = "$sampleName:"
            sampleBinding.sampleNameChb.isChecked = sampleBox.nameChb.isChecked

            sampleBox.nameChb.text = "$sampleName:"

            sampleBinding.sampleNumbers.addTextChangedListener {
                sampleBox.numbers.text = it
            }
            sampleBinding.sampleNameChb.setOnClickListener {
                sampleBox.nameChb.isChecked = sampleBinding.sampleNameChb.isChecked
            }
        }
    }
}
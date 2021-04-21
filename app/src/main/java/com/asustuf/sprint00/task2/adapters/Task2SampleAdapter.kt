package com.asustuf.sprint00.task2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asustuf.sprint00.databinding.Task2ForSampleRvBinding
import com.asustuf.sprint00.dataclasses.Sample
import com.jjoe64.graphview.series.BarGraphSeries
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries

class Task2SampleAdapter(private val samples: MutableList<Sample>) :
    RecyclerView.Adapter<Task2SampleAdapter.Task2SampleHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Task2SampleHolder {
        val binding = Task2ForSampleRvBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return Task2SampleHolder(binding)
    }

    override fun onBindViewHolder(holder: Task2SampleHolder, position: Int) {
        holder.bind(samples[position])
    }

    override fun getItemCount(): Int {
        return samples.size
    }

    class Task2SampleHolder(private val binding: Task2ForSampleRvBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(sample: Sample) {
            val numbers = sample.sampleNumbers
            val numbersSet = numbers.toSortedSet()
            val countsList = mutableListOf<Double>()

            numbersSet.forEach { numberOfSet ->
                var count = 0.0
                numbers.forEach { numberOfArray ->
                    if (numberOfSet.equals(numberOfArray)) {
                        count++
                    }
                }
                countsList.add(count)
            }

            // Line graph
            val lineGraphSeries = LineGraphSeries<DataPoint>().apply { this.isDrawDataPoints = true }
            for (i in numbersSet.indices) {
                lineGraphSeries.appendData(DataPoint(numbersSet.elementAt(i), countsList[i]),
                    true, numbersSet.size, false)
            }
            binding.lineGraph.addSeries(lineGraphSeries)
            binding.lineGraph.title = "Polygon " + sample.sampleName
            binding.lineGraph.titleTextSize = 38F
            binding.lineGraph.viewport.isYAxisBoundsManual = true
            binding.lineGraph.viewport.isXAxisBoundsManual = true

            // Bar graph
            val barGraphSeries = BarGraphSeries<DataPoint>()
            for (i in numbersSet.indices) {
                barGraphSeries.appendData(DataPoint(numbersSet.elementAt(i), countsList[i]),
                    true, numbersSet.size + 10, false)
            }
            binding.barGraph.addSeries(barGraphSeries)
            binding.barGraph.title = "Histogram " + sample.sampleName
            binding.barGraph.titleTextSize = 38F
            binding.barGraph.viewport.isYAxisBoundsManual = true
            binding.barGraph.viewport.isXAxisBoundsManual = true

            // Graph empirical function
            val empiricGraphSeries = LineGraphSeries<DataPoint>().apply { this.isDrawDataPoints = true }
            var count = countsList[0]
            empiricGraphSeries.appendData(DataPoint(numbersSet.elementAt(0), count / numbers.size),
                true, numbersSet.size, false)
            for (i in 1 until numbersSet.size) {
                count += countsList[i]
                empiricGraphSeries.appendData(DataPoint(numbersSet.elementAt(i), count / numbers.size),
                    true, numbersSet.size, false)
            }
            binding.empiricGraph.addSeries(empiricGraphSeries)
            binding.empiricGraph.title = "Empirical graph " + sample.sampleName
            binding.empiricGraph.titleTextSize = 38F
            binding.empiricGraph.viewport.isYAxisBoundsManual = true
            binding.empiricGraph.viewport.isXAxisBoundsManual = true
        }
    }

}
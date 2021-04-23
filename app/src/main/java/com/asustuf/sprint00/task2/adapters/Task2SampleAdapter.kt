 package com.asustuf.sprint00.task2.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asustuf.sprint00.databinding.Task2ForSampleRvBinding
import com.asustuf.sprint00.model.dataclasses.Sample
import com.asustuf.sprint00.model.SampleOperations
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
            val sampleOperations = SampleOperations(sample.sampleNumbers.toTypedArray())
            val numbersSet = sampleOperations.getNumbersSortedSet()
            val countsList = sampleOperations.getCountsList()

            // Line graph
            val lineGraphSeries = LineGraphSeries<DataPoint>().apply {
                this.isDrawDataPoints = true
            }
            for (i in numbersSet.indices) {
                lineGraphSeries.appendData(DataPoint(numbersSet.elementAt(i), countsList[i]),
                    true, numbersSet.size, false)
            }
            binding.lineGraph.addSeries(lineGraphSeries)
            binding.lineGraph.title = "Polygon " + sample.sampleName
            binding.lineGraph.titleTextSize = 38F
            binding.lineGraph.viewport.isScalable = true
            binding.lineGraph.setBackgroundColor(Color.rgb(10, 20, 100))

            // Bar graph
            val barGraphSeries = BarGraphSeries<DataPoint>().apply {
                this.isDrawValuesOnTop = true
                this.valuesOnTopColor = Color.YELLOW
            }
            for (i in numbersSet.indices) {
                barGraphSeries.appendData(DataPoint(numbersSet.elementAt(i), countsList[i]),
                    true, numbersSet.size + 10, false)
            }
            binding.barGraph.addSeries(barGraphSeries)
            binding.barGraph.title = "Histogram " + sample.sampleName
            binding.barGraph.titleTextSize = 38F
            binding.barGraph.viewport.isScalable = true
            binding.barGraph.setBackgroundColor(Color.rgb(10, 20, 100))

            // Graph empirical function
            val empiricGraphSeries = LineGraphSeries<DataPoint>().apply { this.isDrawDataPoints = true }
            var count = countsList[0]
            empiricGraphSeries.appendData(DataPoint(numbersSet.elementAt(0),
                count / sampleOperations.getNumbers().size),
                true, numbersSet.size, false)
            for (i in 1 until numbersSet.size) {
                count += countsList[i]
                empiricGraphSeries.appendData(DataPoint(numbersSet.elementAt(i),
                    count / sampleOperations.getNumbers().size),
                    true, numbersSet.size, false)
            }
            binding.empiricGraph.addSeries(empiricGraphSeries)
            binding.empiricGraph.title = "Empirical graph " + sample.sampleName
            binding.empiricGraph.titleTextSize = 38F
            binding.empiricGraph.viewport.isScalable = true
            binding.empiricGraph.setBackgroundColor(Color.rgb(10, 20, 100))
        }
    }

}
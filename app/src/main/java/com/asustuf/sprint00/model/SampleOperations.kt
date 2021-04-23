package com.asustuf.sprint00.model

import android.util.Log
import kotlin.math.absoluteValue
import kotlin.math.pow
import kotlin.math.round
import kotlin.math.sqrt

class SampleOperations(private val numbers: Array<Double>, private val accuracy: Int = 5) {
    private val numbersSet = numbers.toSortedSet()
    private val countsList = mutableListOf<Double>()

    init {
        numbersSet.forEach { numberOfSet ->
            var count = 0.0
            numbers.forEach { numberOfArray ->
                if (numberOfSet == numberOfArray) {
                    count++
                }
            }
            countsList.add(count)
        }
    }

    private val sampleSize = numbers.size
    private val variationRow = numbers.sorted()

    private val _cumulativeFrequency: Array<Double> by lazy {
        val cumulativeFrequency = mutableListOf(countsList[0])
        for (i in 1 until countsList.size) {
            cumulativeFrequency.add(cumulativeFrequency[i - 1] + countsList[i])
        }
        cumulativeFrequency.toTypedArray()
    }
    private val _relativeFrequency: Array<Double> by lazy {
        val relativeFrequency = mutableListOf<Double>()
        for (i in numbersSet.indices) {
            relativeFrequency.add(countsList[i] / sampleSize.toDouble())
        }
        relativeFrequency.toTypedArray()
    }
    private val _cumulativeRelativeFrequency: Array<Double> by lazy {
        val cumulativeFrequency = this.cumulativeFrequency
        val cumulativeRelativeFrequency = mutableListOf<Double>()
        cumulativeFrequency.forEach {
            cumulativeRelativeFrequency.add(it / sampleSize)
        }
        cumulativeRelativeFrequency.toTypedArray()
    }

    val cumulativeFrequency get() = _cumulativeFrequency.copyOf()
    val relativeFrequency get() = _relativeFrequency.copyOf()
    val cumulativeRelativeFrequency get() = _cumulativeRelativeFrequency.copyOf()

    val span by lazy { numbers.maxOrNull()!! - numbers.minOrNull()!! }
    val sampleAverage by lazy { roundTo(numbers.average(), accuracy) }
    val median by lazy {
        if (variationRow.size % 2 == 0) {
            variationRow[variationRow.size / 2]
        } else {
            (variationRow[variationRow.size / 2 - 1] + variationRow[variationRow.size / 2]) / 2
        }
    }
    val moda by lazy { numbersSet.elementAt(countsList.indexOf(countsList.maxOrNull())) }
    val selectiveVariance by lazy {
        var selectiveVariance = 0.0
        for (i in numbersSet.indices) {
            selectiveVariance += (numbersSet.elementAt(i) - sampleAverage).pow(2) * countsList[i]
        }
        selectiveVariance /= numbers.size
        roundTo(selectiveVariance, accuracy)
    }

    val sampleStandardDeviation by lazy { roundTo(sqrt(selectiveVariance), accuracy) }
    val coefficientOfVariation by lazy { roundTo(sampleStandardDeviation / sampleAverage, 2).absoluteValue }
    val centralMoment3 by lazy { roundTo(getCentralMoment(3), accuracy) }
    val centralMoment4 by lazy { roundTo(getCentralMoment(4), accuracy) }
    val asymmetry by lazy { roundTo(centralMoment3 / sampleStandardDeviation.pow(3), accuracy) }
    val excess by lazy { roundTo(centralMoment4 / sampleStandardDeviation.pow(4) - 3, accuracy)}

    fun getNumbers() = numbers.copyOf()
    fun getCountsList() = countsList.toTypedArray()
    fun getNumbersSortedSet() = numbersSet.toTypedArray()
    fun getVariationRow() = variationRow.toTypedArray().copyOf()

    private fun getCentralMoment(degree: Int): Double {
        var centralMoment = 0.0
        for (i in numbers.indices) {
            centralMoment += (numbers[i] - sampleAverage).pow(degree) * getNumbersProbability(i)
        }
        return centralMoment / numbers.size
    }

    private fun getNumbersProbability(index: Int): Double {
        return if (index in numbers.indices) {
            countsList[numbersSet.indexOf(numbers[index])] / numbers.size
        } else { 0.0 }
    }

    private fun roundTo(number: Double, accuracy: Int) =
        round(number * 10.0.pow(accuracy)) / 10.0.pow(accuracy)
}
package com.asustuf.sprint00.model

class SampleOperations(private val numbers: Array<Double>) {

    private val numbersSet = numbers.toSortedSet()
    private val countsList = mutableListOf<Double>()
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

    fun getNumbers() = numbers.copyOf()

    fun getCountsList() = countsList.toTypedArray()

    fun getNumbersSortedSet() = numbersSet.toTypedArray()

    fun getSpan() = numbers.maxOrNull()!! - numbers.minOrNull()!!

    fun getVariationRow() = variationRow

//    fun getCumulativeFrequency(): Array<Double> {
//        return if (::cumulativeFrequency.isInitialized) {
//            cumulativeFrequency
//        } else {
//            val cumulativeFrequency = mutableListOf(countsList[0])
//            for (i in 1 until countsList.size) {
//                cumulativeFrequency.add(cumulativeFrequency[i - 1] + countsList[i])
//            }
//            this.cumulativeFrequency = cumulativeFrequency.toTypedArray()
//            cumulativeFrequency.toTypedArray()
//        }
//    }

//    fun getRelativeFrequency(): Array<Double> {
//        return if (::relativeFrequency.isInitialized) {
//            relativeFrequency
//        } else {
//            val relativeFrequency = mutableListOf<Double>()
//            for (i in numbersSet.indices) {
//                relativeFrequency.add(countsList[i] / sampleSize.toDouble())
//            }
//            this.relativeFrequency = relativeFrequency.toTypedArray()
//            relativeFrequency.toTypedArray()
//        }
//    }

//    fun getCumulativeRelativeFrequency(): Array<Double> {
//        return if (::cumulativeRelativeFrequency.isInitialized) {
//            cumulativeRelativeFrequency
//        } else {
//            val cumulativeFrequency = if (::cumulativeFrequency.isInitialized) {
//                this.cumulativeFrequency
//            } else {
//                getCumulativeFrequency()
//            }
//            val cumulativeRelativeFrequency = mutableListOf<Double>()
//            cumulativeFrequency.forEach {
//                cumulativeRelativeFrequency.add(it.toDouble() / sampleSize)
//            }
//            this.cumulativeRelativeFrequency = cumulativeRelativeFrequency.toTypedArray()
//            cumulativeRelativeFrequency.toTypedArray()
//        }
//    }

    fun getMedian(): Double {
        return if (variationRow.size % 2 == 0) {
            variationRow[variationRow.size / 2]
        } else {
            (variationRow[variationRow.size / 2 - 1] + variationRow[variationRow.size / 2]) / 2
        }
    }

    fun getModa() = numbersSet.elementAt(countsList.indexOf(countsList.maxOrNull()))


}
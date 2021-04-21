package com.asustuf.sprint00.dataclasses

import java.io.Serializable

data class Sample(val sampleName: String, val sampleNumbers: MutableList<Double>) : Serializable

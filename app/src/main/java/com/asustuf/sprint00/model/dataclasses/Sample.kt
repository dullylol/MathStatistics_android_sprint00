package com.asustuf.sprint00.model.dataclasses

import java.io.Serializable

data class Sample(val sampleName: String, val sampleNumbers: MutableList<Double>) : Serializable

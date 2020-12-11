package io.vividcode.kotlinserializationexample

import kotlinx.serialization.Serializable

@Serializable
data class TestData(
    val v1: String,
    val v2: Int,
    val v3: Boolean
)

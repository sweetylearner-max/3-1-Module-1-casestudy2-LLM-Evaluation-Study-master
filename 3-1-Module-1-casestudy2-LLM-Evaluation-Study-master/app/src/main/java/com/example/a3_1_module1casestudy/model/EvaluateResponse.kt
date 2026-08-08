package com.example.a3_1_module1casestudy.model

data class EvaluateResponse(
    val status: String,
    val accuracy: Double,
    val coherence: Double,
    val perplexity: Double
)
package com.example.a3_1_module1casestudy.network

import com.example.a3_1_module1casestudy.model.EvaluateRequest
import com.example.a3_1_module1casestudy.model.EvaluateResponse
import com.example.a3_1_module1casestudy.model.GenerateRequest
import com.example.a3_1_module1casestudy.model.GenerateResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("generate")
    fun generateResponse(@Body request: GenerateRequest): Call<GenerateResponse>

    @POST("evaluate")
    fun evaluateResponse(@Body request: EvaluateRequest): Call<EvaluateResponse>
}
package com.example.a3_1_module1casestudy

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a3_1_module1casestudy.databinding.ActivityMainBinding
import com.example.a3_1_module1casestudy.model.EvaluateRequest
import com.example.a3_1_module1casestudy.model.EvaluateResponse
import com.example.a3_1_module1casestudy.model.GenerateRequest
import com.example.a3_1_module1casestudy.model.GenerateResponse
import com.example.a3_1_module1casestudy.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        binding.btnGenerate.setOnClickListener {
            val prompt = binding.etPrompt.text.toString().trim()
            if (prompt.isEmpty()) {
                Toast.makeText(this, "Please enter a prompt", Toast.LENGTH_SHORT).show()
            } else {
                generateAndEvaluate(prompt)
            }
        }
    }

    private fun generateAndEvaluate(prompt: String) {
        showLoading(true)
        resetResults()

        ApiClient.apiService.generateResponse(GenerateRequest(prompt))
            .enqueue(object : Callback<GenerateResponse> {
                override fun onResponse(call: Call<GenerateResponse>, response: Response<GenerateResponse>) {
                    if (response.isSuccessful && response.body() != null) {
                        val generatedText = response.body()!!.response
                        binding.txtResponse.text = generatedText
                        
                        // Proceed to evaluate
                        evaluateResponse(prompt, generatedText)
                    } else {
                        showLoading(false)
                        showError("Failed to generate response: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<GenerateResponse>, t: Throwable) {
                    showLoading(false)
                    showError("Error: ${t.message ?: "Unknown error"}")
                }
            })
    }

    private fun evaluateResponse(prompt: String, generatedResponse: String) {
        ApiClient.apiService.evaluateResponse(EvaluateRequest(prompt, generatedResponse))
            .enqueue(object : Callback<EvaluateResponse> {
                override fun onResponse(call: Call<EvaluateResponse>, response: Response<EvaluateResponse>) {
                    showLoading(false)
                    if (response.isSuccessful && response.body() != null) {
                        val eval = response.body()!!
                        binding.txtAccuracy.text = "${eval.accuracy}%"
                        binding.txtCoherence.text = "${eval.coherence}%"
                        binding.txtPerplexity.text = String.format("%.2f", eval.perplexity)
                    } else {
                        showError("Failed to evaluate response: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<EvaluateResponse>, t: Throwable) {
                    showLoading(false)
                    showError("Evaluation Error: ${t.message ?: "Unknown error"}")
                }
            })
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.btnGenerate.isEnabled = !isLoading
        binding.etPrompt.isEnabled = !isLoading
    }

    private fun resetResults() {
        binding.txtResponse.text = "Response will appear here..."
        binding.txtAccuracy.text = "--%"
        binding.txtCoherence.text = "--%"
        binding.txtPerplexity.text = "--"
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
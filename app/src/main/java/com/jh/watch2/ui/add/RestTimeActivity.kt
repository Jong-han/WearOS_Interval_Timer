package com.jh.watch2.ui.add

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.jh.watch2.databinding.ActivityAddTimerRestTimeBinding

class RestTimeActivity: ComponentActivity() {

    private lateinit var binding: ActivityAddTimerRestTimeBinding
    private val viewModel: RestTimeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddTimerRestTimeBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setContentView(binding.root)

        viewModel.clickNext.observe(this, {
            setResult(Activity.RESULT_OK)
            finish()
        })

        binding.npMinute.maxValue = 60
        binding.npMinute.minValue = 0
        binding.npSecond.maxValue = 60
        binding.npSecond.minValue = 0
    }
    private val nextStepResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode== Activity.RESULT_OK) {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}
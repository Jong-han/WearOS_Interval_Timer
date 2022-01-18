package com.jh.watch2.ui.add

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.jh.watch2.databinding.ActivityAddTimerSetTimeBinding

class SetTimeActivity: ComponentActivity() {

    private lateinit var binding: ActivityAddTimerSetTimeBinding
    private val viewModel: SetTimeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddTimerSetTimeBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setContentView(binding.root)

        viewModel.clickNext.observe(this, {
            nextStepResult.launch(Intent(this, RestTimeActivity::class.java))
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
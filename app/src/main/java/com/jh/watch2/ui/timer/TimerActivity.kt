package com.jh.watch2.ui.timer

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.jh.watch2.databinding.ActivityTimerBinding

class TimerActivity : ComponentActivity() {
    private lateinit var binding: ActivityTimerBinding
    private val viewModel: TimerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTimerBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            binding.progressCircular.startTimer()
        }

        binding.progressCircular.run {
            setOnTimerFinishedListener {
                if (viewModel.isRemainSet()) {
                    viewModel.timeType.value?.let { viewModel.switchTimeType(it) }
                    binding.progressCircular.startTimer()
                }
            }
        }

        viewModel.timeType.observe(this, {
            binding.progressCircular.totalTime = it.time
            if (it is TimerViewModel.TimeType.Set) {
                binding.progressCircular.setColorSchemeColors(Color.BLUE)
            } else {
                binding.progressCircular.setColorSchemeColors(Color.RED)
            }
        })

    }
}
package com.jh.watch2.ui.add

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.jh.watch2.databinding.ActivityAddTimerRestTimeBinding
import com.jh.watch2.ui.main.MainActivity
import com.jh.watch2.ui.timer.TimerActivity

class RestTimeActivity: ComponentActivity() {

    private lateinit var binding: ActivityAddTimerRestTimeBinding
    private val viewModel: RestTimeViewModel by viewModels()

    companion object {
        const val EXTRA_TIMER_MODEL = "EXTRA_TIMER_MODEL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddTimerRestTimeBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setContentView(binding.root)

        viewModel.clickNext.observe(this, {
            if (viewModel.getWhereFrom() == 0) {
                Intent(this, MainActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    putExtra(EXTRA_TIMER_MODEL, viewModel.convertDataToTimerModel(binding.npMinute.value, binding.npSecond.value))
                    viewModel.insertTimer(binding.npMinute.value, binding.npSecond.value)
                    startActivity(this)
                }
            } else {
                Intent(this, TimerActivity::class.java).apply {
                    putExtra(EXTRA_TIMER_MODEL, viewModel.convertDataToTimerModel(binding.npMinute.value, binding.npSecond.value))
                    viewModel.insertTimer(binding.npMinute.value, binding.npSecond.value)
                    startActivity(this)
                }
            }
            finish()
        })

        binding.npMinute.maxValue = 59
        binding.npMinute.minValue = 0
        binding.npSecond.maxValue = 59
        binding.npSecond.minValue = 0
    }
}
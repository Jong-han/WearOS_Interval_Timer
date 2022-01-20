package com.jh.watch2.ui.add

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.jh.watch2.databinding.ActivityAddTimerRestTimeBinding
import com.jh.watch2.databinding.ActivityAddTimerSetCountBinding
import com.jh.watch2.ui.timer.TimerActivity

class SetCountActivity: ComponentActivity() {

    companion object {
        const val EXTRA_SET_COUNT = "EXTRA_SET_COUNT"
    }

    private lateinit var binding: ActivityAddTimerSetCountBinding
    private val viewModel: SetCountViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddTimerSetCountBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setContentView(binding.root)

        viewModel.clickNext.observe(this, {
            Intent(this, SetTimeActivity::class.java).apply {
                putExtra(EXTRA_SET_COUNT, binding.npSetCount.value)
                putExtra(TimerActivity.EXTRA_FROM, viewModel.getWhereFrom())
                startActivity(this)
            }
        })

        binding.npSetCount.maxValue = 60
        binding.npSetCount.minValue = 0

    }
}
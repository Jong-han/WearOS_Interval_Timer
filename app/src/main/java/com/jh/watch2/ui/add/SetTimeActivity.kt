package com.jh.watch2.ui.add

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.jh.watch2.databinding.ActivityAddTimerSetTimeBinding
import com.jh.watch2.ui.timer.TimerActivity

class SetTimeActivity: ComponentActivity() {

    companion object {
        const val EXTRA_SET_TIME = "EXTRA_SET_TIME"
    }

    private lateinit var binding: ActivityAddTimerSetTimeBinding
    private val viewModel: SetTimeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddTimerSetTimeBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setContentView(binding.root)

        viewModel.clickNext.observe(this, {
            Intent(this, RestTimeActivity::class.java).apply {
                putExtra(SetCountActivity.EXTRA_SET_COUNT, viewModel.getSetCount())
                putExtra(EXTRA_SET_TIME, viewModel.convertTimeToLong(binding.npMinute.value, binding.npSecond.value))
                putExtra(TimerActivity.EXTRA_FROM, viewModel.getWhereFrom())
                startActivity(this)
            }
        })

        binding.npMinute.maxValue = 59
        binding.npMinute.minValue = 0
        binding.npSecond.maxValue = 59
        binding.npSecond.minValue = 0

    }
    private val nextStepResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode== Activity.RESULT_OK) {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}
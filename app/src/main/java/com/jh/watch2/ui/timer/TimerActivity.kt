package com.jh.watch2.ui.timer

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Vibrator
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.jh.watch2.databinding.ActivityTimerBinding
import com.jh.watch2.ui.add.SetCountActivity

@Suppress("DEPRECATION")
class TimerActivity : ComponentActivity() {

    companion object {
        const val EXTRA_FROM = "EXTRA_FROM"
    }

    private lateinit var binding: ActivityTimerBinding
    private val viewModel: TimerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTimerBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)

        viewModel.clickStart.observe(this, {
            binding.progressCircular.startTimer()
        })

        viewModel.clickEdit.observe(this, {
            Intent(this, SetCountActivity::class.java).apply {
                putExtra(EXTRA_FROM,1)
                startActivity(this)
            }
        })

        binding.progressCircular.run {
            setOnTimerFinishedListener {
                val vibration = if (Build.VERSION.SDK_INT >= 31) {
                    getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as Vibrator
                } else {
                    getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                }
                vibration.vibrate(500)
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
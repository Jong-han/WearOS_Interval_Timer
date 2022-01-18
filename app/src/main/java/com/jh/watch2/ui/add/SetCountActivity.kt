package com.jh.watch2.ui.add

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.jh.watch2.databinding.ActivityAddTimerRestTimeBinding
import com.jh.watch2.databinding.ActivityAddTimerSetCountBinding

class SetCountActivity: ComponentActivity() {

    private lateinit var binding: ActivityAddTimerSetCountBinding
    private val viewModel: SetCountViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddTimerSetCountBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setContentView(binding.root)

        viewModel.clickNext.observe(this, {
            nextStepResult.launch(Intent(this, SetTimeActivity::class.java))
        })

        binding.npSetCount.maxValue = 60
        binding.npSetCount.minValue = 0

    }
    private val nextStepResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode== Activity.RESULT_OK) {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}
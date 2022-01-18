package com.jh.watch2.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.wear.widget.WearableLinearLayoutManager
import com.jh.watch2.TimerModel
import com.jh.watch2.databinding.ActivityMainBinding
import com.jh.watch2.ui.add.SetCountActivity
import com.jh.watch2.ui.timer.TimerActivity
import com.jh.watch2.util.CustomScrollingLayoutCallback

class MainActivity : ComponentActivity() {

    companion object {
        const val EXTRA_TIMER = "EXTRA_TIMER"
    }

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val mainAdapter by lazy { MainAdapter(clickTimer) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)

        binding.recyclerLauncherView.run {
            isEdgeItemsCenteringEnabled = true
            layoutManager = WearableLinearLayoutManager(this@MainActivity, CustomScrollingLayoutCallback())
            adapter = mainAdapter
        }

        viewModel.clickAdd.observe(this, {
            addTimerResult.launch( Intent(this, SetCountActivity::class.java) )
        })

        viewModel.timerList.observe(this, {
            mainAdapter.submitList( it.toList() )
        })

    }
    private val addTimerResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode== Activity.RESULT_OK) {
            viewModel.addTimer()
        }
    }

    private val clickTimer: (TimerModel)->Unit = {
        Intent(this, TimerActivity::class.java).apply {
            putExtra(EXTRA_TIMER, it)
            startActivity(this)
        }
    }

}
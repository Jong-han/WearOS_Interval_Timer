package com.jh.watch2.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.wear.widget.WearableLinearLayoutManager
import com.jh.watch2.db.TimerModel
import com.jh.watch2.databinding.ActivityMainBinding
import com.jh.watch2.ui.add.RestTimeActivity
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

        android.util.Log.i("asdf", "onCreate")

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)

        binding.recyclerLauncherView.run {
            isEdgeItemsCenteringEnabled = true
            layoutManager =
                WearableLinearLayoutManager(this@MainActivity, CustomScrollingLayoutCallback())
            adapter = mainAdapter
        }

        viewModel.clickAdd.observe(this, {
            Intent(this, SetCountActivity::class.java).apply {
                putExtra(TimerActivity.EXTRA_FROM, 0)
                startActivity(this)
            }
        })

        viewModel.timerList.observe(this, {
            mainAdapter.submitList(it.toList())
        })

    }

    private val clickTimer: (TimerModel)->Unit = {
        Intent(this, TimerActivity::class.java).apply {
            putExtra(EXTRA_TIMER, it)
            startActivity(this)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val timerModel = intent?.getParcelableExtra<TimerModel>(RestTimeActivity.EXTRA_TIMER_MODEL)
//        timerModel?.let {
//            viewModel.addTimer(it)
//        }
    }

}
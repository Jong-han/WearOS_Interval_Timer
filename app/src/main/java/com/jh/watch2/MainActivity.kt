package com.jh.watch2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.wear.widget.WearableLinearLayoutManager
import com.jh.watch2.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val mainAdapter by lazy { MainAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)

        binding.recyclerLauncherView.run {
            isEdgeItemsCenteringEnabled = false
            layoutManager = WearableLinearLayoutManager(this@MainActivity)
            adapter = mainAdapter
        }

    }

}
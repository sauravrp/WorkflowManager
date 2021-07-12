package com.sauravrp.takehome.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.sauravrp.takehome.WorkFlowViewModel
import com.sauravrp.takehome.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var workFlowViewModel: WorkFlowViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        workFlowViewModel = ViewModelProvider(this).get(WorkFlowViewModel::class.java)

        binding.listView.apply {
            adapter = WorkAdapter()
        }

        workFlowViewModel.viewState.observe(this, { state ->
            when(state) {
                is WorkFlowViewModel.ViewState.Success ->
                    (binding.listView.adapter as WorkAdapter).submitList(state.data)
            }
        })
    }
}
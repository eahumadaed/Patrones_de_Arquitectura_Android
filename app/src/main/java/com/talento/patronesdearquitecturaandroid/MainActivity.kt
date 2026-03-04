package com.talento.patronesdearquitecturaandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.talento.patronesdearquitecturaandroid.databinding.ActivityMainBinding
import com.talento.patronesdearquitecturaandroid.ui.TaskAdapter
import com.talento.patronesdearquitecturaandroid.viewmodel.TaskListViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var taskListViewModel: TaskListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        taskListViewModel = ViewModelProvider(this)[TaskListViewModel::class.java]
        binding.viewModel = taskListViewModel

        setupRecyclerView()
        observeTaskList()
    }

    private fun setupRecyclerView() {
        taskAdapter = TaskAdapter { position, isChecked ->
            taskListViewModel.updateTaskCompletion(position, isChecked)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = taskAdapter
    }

    private fun observeTaskList() {
        taskListViewModel.taskListLiveData.observe(this, taskAdapter::submitList)
    }
}

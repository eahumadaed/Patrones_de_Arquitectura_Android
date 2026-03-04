package com.talento.patronesdearquitecturaandroid;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.talento.patronesdearquitecturaandroid.databinding.ActivityMainBinding;
import com.talento.patronesdearquitecturaandroid.ui.TaskAdapter;
import com.talento.patronesdearquitecturaandroid.viewmodel.TaskListViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private TaskAdapter taskAdapter;
    private TaskListViewModel taskListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);

        taskListViewModel = new ViewModelProvider(this).get(TaskListViewModel.class);
        binding.setViewModel(taskListViewModel);

        setupRecyclerView();
        observeTaskList();
    }

    private void setupRecyclerView() {
        taskAdapter = new TaskAdapter((position, isChecked) ->
                taskListViewModel.updateTaskCompletion(position, isChecked));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(taskAdapter);
    }

    private void observeTaskList() {
        taskListViewModel.getTaskListLiveData().observe(this, taskAdapter::submitList);
    }
}

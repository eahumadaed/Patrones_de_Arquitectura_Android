package com.talento.patronesdearquitecturaandroid.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.talento.patronesdearquitecturaandroid.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskListViewModel extends ViewModel {
    private final MutableLiveData<List<Task>> taskListLiveData = new MutableLiveData<>();

    public TaskListViewModel() {
        taskListLiveData.setValue(createInitialTasks());
    }

    public LiveData<List<Task>> getTaskListLiveData() {
        return taskListLiveData;
    }

    public void updateTaskCompletion(int position, boolean isCompleted) {
        List<Task> currentTasks = taskListLiveData.getValue();
        if (currentTasks == null || position < 0 || position >= currentTasks.size()) {
            return;
        }

        List<Task> updatedTasks = new ArrayList<>(currentTasks.size());
        for (int i = 0; i < currentTasks.size(); i++) {
            Task existingTask = currentTasks.get(i);
            Task copiedTask = new Task(existingTask.getTitle(), existingTask.isCompleted());
            if (i == position) {
                copiedTask.setCompleted(isCompleted);
            }
            updatedTasks.add(copiedTask);
        }
        taskListLiveData.setValue(updatedTasks);
    }

    private List<Task> createInitialTasks() {
        List<Task> initialTasks = new ArrayList<>();
        initialTasks.add(new Task("Estudiar MVVM", false));
        initialTasks.add(new Task("Implementar RecyclerView", false));
        initialTasks.add(new Task("Conectar LiveData", false));
        initialTasks.add(new Task("Probar en dispositivo real", false));
        return initialTasks;
    }
}

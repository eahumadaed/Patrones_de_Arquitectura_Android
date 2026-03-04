package com.talento.patronesdearquitecturaandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.talento.patronesdearquitecturaandroid.model.Task

class TaskListViewModel : ViewModel() {
    private val taskListMutableLiveData = MutableLiveData(createInitialTasks())
    val taskListLiveData: LiveData<List<Task>> = taskListMutableLiveData

    fun updateTaskCompletion(position: Int, isCompleted: Boolean) {
        val currentTasks = taskListMutableLiveData.value ?: return
        if (position !in currentTasks.indices) return

        val updatedTasks = currentTasks.mapIndexed { index, task ->
            if (index == position) {
                task.copy(isCompleted = isCompleted)
            } else {
                task.copy()
            }
        }
        taskListMutableLiveData.value = updatedTasks
    }

    private fun createInitialTasks(): List<Task> = listOf(
        Task(title = "Estudiar MVVM", isCompleted = false),
        Task(title = "Implementar RecyclerView", isCompleted = false),
        Task(title = "Conectar LiveData", isCompleted = false),
        Task(title = "Probar en dispositivo real", isCompleted = false)
    )
}

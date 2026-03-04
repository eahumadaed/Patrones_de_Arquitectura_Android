# Patrones de Arquitectura Android - M5_AE3

Aplicacion Android nativa desarrollada 100% en Kotlin para resolver el ejercicio de MVVM + LiveData.

## Tecnologias

- Kotlin
- MVVM
- LiveData
- RecyclerView
- Data Binding

## Objetivo

Mostrar una lista de tareas y permitir marcar cada tarea como completada o no completada, actualizando automaticamente la UI cuando cambia el estado.

## Implementacion

1. Modelo `Task` con atributos `title` e `isCompleted`.
2. `TaskListViewModel` con `LiveData<List<Task>>` para exponer estado y actualizar tareas.
3. `activity_main.xml` con `RecyclerView` para listar tareas.
4. `item_task.xml` con Data Binding para mostrar titulo y checkbox.
5. `TaskAdapter` para enlazar lista + eventos de cambio en checkbox.
6. `MainActivity` para configurar ViewModel, RecyclerView y observacion de LiveData.

## Estructura de archivos

- `app/src/main/java/com/talento/patronesdearquitecturaandroid/MainActivity.kt`
- `app/src/main/java/com/talento/patronesdearquitecturaandroid/model/Task.kt`
- `app/src/main/java/com/talento/patronesdearquitecturaandroid/viewmodel/TaskListViewModel.kt`
- `app/src/main/java/com/talento/patronesdearquitecturaandroid/ui/TaskAdapter.kt`
- `app/src/main/res/layout/activity_main.xml`
- `app/src/main/res/layout/item_task.xml`
- `app/src/main/AndroidManifest.xml`
- `app/build.gradle.kts`
- `gradle/libs.versions.toml`

## Requisitos

- Android SDK instalado
- Java 11

## Compilar

```powershell
.\gradlew.bat assembleDebug
```
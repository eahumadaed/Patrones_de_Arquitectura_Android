# Patrones de Arquitectura Android - M5_AE3

Aplicacion Android nativa (Java) que implementa una lista de tareas usando:

- MVVM
- LiveData
- RecyclerView
- Data Binding

El proyecto resuelve el ejercicio solicitado en el documento **M5_AE3_Ejercicio_Patrones de Arquitectura Android**.

## Objetivo del ejercicio

Construir una app de lista de tareas donde el usuario pueda:

- Ver tareas
- Marcar cada tarea como completada/no completada
- Ver reflejado el cambio automaticamente en la interfaz

## Implementacion realizada

Se implementaron todos los puntos del desafio:

1. Clase `Task` con atributos `title` e `isCompleted`.
2. `TaskListViewModel` con `LiveData<List<Task>>` para manejar estado.
3. `RecyclerView` en `activity_main.xml`.
4. Adaptador `TaskAdapter` para enlazar datos y manejar cambios en checkbox.
5. `item_task.xml` con Data Binding para mostrar `title` y `completed`.
6. `MainActivity` configurando ViewModel, RecyclerView, adapter y observacion de LiveData.
7. Carga inicial en memoria (sin persistencia), tal como pide el enunciado.

## Arquitectura

### Capa modelo

- `Task`: entidad de dominio simple con titulo y estado.

### Capa ViewModel

- `TaskListViewModel`:
  - Expone `LiveData<List<Task>>`.
  - Inicializa lista en memoria.
  - Actualiza estado de una tarea (`updateTaskCompletion`) y publica nueva lista.

### Capa UI

- `MainActivity`:
  - Obtiene ViewModel con `ViewModelProvider`.
  - Configura `RecyclerView`.
  - Observa LiveData y refresca el adapter.
- `TaskAdapter`:
  - Renderiza tareas con `ItemTaskBinding`.
  - Notifica a la actividad cuando cambia un checkbox.

## Estructura de archivos

- `app/src/main/java/com/talento/patronesdearquitecturaandroid/MainActivity.java`
- `app/src/main/java/com/talento/patronesdearquitecturaandroid/model/Task.java`
- `app/src/main/java/com/talento/patronesdearquitecturaandroid/viewmodel/TaskListViewModel.java`
- `app/src/main/java/com/talento/patronesdearquitecturaandroid/ui/TaskAdapter.java`
- `app/src/main/res/layout/activity_main.xml`
- `app/src/main/res/layout/item_task.xml`
- `app/src/main/AndroidManifest.xml`
- `app/build.gradle.kts`
- `gradle/libs.versions.toml`

## Requisitos tecnicos

- Android SDK configurado (`local.properties`)
- Java 11
- Gradle Wrapper incluido en el repo
- ADB disponible para instalacion en dispositivo real

## Compilacion

Desde la raiz del proyecto:

```powershell
.\gradlew.bat assembleDebug
```

APK generado:

```text
app/build/outputs/apk/debug/app-debug.apk
```
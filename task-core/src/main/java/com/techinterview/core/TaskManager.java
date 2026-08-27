package com.techinterview.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;
    private final String filePath = "tasks.json";
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public TaskManager() {
        loadTasks();
    }

    public void addTask(String desc) {
        tasks.add(new Task(desc));
        saveTasks();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    private void saveTasks() {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(tasks, writer);
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    private void loadTasks() {
        File file = new File(filePath);
        if (!file.exists()) {
            tasks = new ArrayList<>();
            return;
        }
        try (Reader reader = new FileReader(file)) {
            Type listType = new TypeToken<ArrayList<Task>>(){}.getType();
            tasks = gson.fromJson(reader, listType);
            if (tasks == null) tasks = new ArrayList<>();
        } catch (IOException e) {
            tasks = new ArrayList<>();
        }
    }
}

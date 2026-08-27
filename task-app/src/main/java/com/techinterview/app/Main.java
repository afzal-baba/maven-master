package com.techinterview.app;

import com.techinterview.core.TaskManager;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Task Manager (Multi-Module) ===");
        while (true) {
            System.out.print("> ");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("exit")) break;
            if (input.equalsIgnoreCase("list")) {
                manager.getTasks().forEach(System.out::println);
            } else if (!input.isBlank()) {
                manager.addTask(input);
                System.out.println("Added!");
            }
        }
        sc.close();
    }
}

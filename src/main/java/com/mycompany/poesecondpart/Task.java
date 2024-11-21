/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poesecondpart;

import java.util.ArrayList;
import java.util.UUID;
import javax.swing.JOptionPane;

/**
 *
 * @author RC_Student_lab
 */
public class Task {
    
     private String taskName;
    private int taskNumber;
    private String taskDescription;
    private String developerDetails;
    private int taskDuration;
    private String taskStatus;
    private String taskID;

    public Task(String taskName, int taskNumber, String taskDescription, String developerDetails, int taskDuration, String taskStatus) {
        this.taskName = taskName;
        this.taskNumber = taskNumber;
        this.taskDescription = taskDescription;
        this.developerDetails = developerDetails;
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
        this.taskID = createTaskID();
    }

    // Check if the task description is not more than 50 characters
    public boolean checkTaskDescription() {
        return taskDescription.length() <= 50;
    }

    // Create the Task ID in the format specified
    public String createTaskID() {
        String firstTwoLetters = taskName.substring(0, 2).toUpperCase();
        String lastThreeLetters = developerDetails.substring(developerDetails.length() - 3).toUpperCase();
        return firstTwoLetters + ":" + taskNumber + ":" + lastThreeLetters;
    }

    // Return the task details
    public String printTaskDetails() {
        return "Task Status: " + taskStatus +
               "\nDeveloper Details: " + developerDetails +
               "\nTask Number: " + taskNumber +
               "\nTask Name: " + taskName +
               "\nTask Description: " + taskDescription +
               "\nTask ID: " + taskID +
               "\nTask Duration: " + taskDuration + " hours";
    }

    // Return the total duration for all tasks
    public int returnTotalHours() {
        return taskDuration;
    }
    
    public class TaskManagementSystem {
    // Arrays to store task information
    private static ArrayList<String> developers = new ArrayList<>();
    private static ArrayList<String> taskNames = new ArrayList<>();
    private static ArrayList<String> taskIDs = new ArrayList<>();
    private static ArrayList<Integer> taskDurations = new ArrayList<>();
    private static ArrayList<String> taskStatuses = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            // Main menu options
            String[] options = {
                "Add Task", 
                "Display All Done Tasks", 
                "Find Longest Task", 
                "Search Task by Name", 
                "Search Tasks by Developer", 
                "Delete Task", 
                "Display Full Task Report", 
                "Exit"
            };

            // Show main menu
            int choice = JOptionPane.showOptionDialog(
                null, 
                "Task Management System", 
                "Main Menu", 
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, 
                null, 
                options, 
                options[0]
            );

            // Handle user choice
            switch (choice) {
                case 0:
                    addTask();
                    break;
                case 1:
                    displayDoneTasks();
                    break;
                case 2:
                    findLongestTask();
                    break;
                case 3:
                    searchTaskByName();
                    break;
                case 4:
                    searchTasksByDeveloper();
                    break;
                case 5:
                    deleteTask();
                    break;
                case 6:
                    displayFullTaskReport();
                    break;
                case 7:
                    System.exit(0);
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option selected!");
            }
        }
    }

    // Method to add a new task
    private static void addTask() {
        String developer = JOptionPane.showInputDialog("Enter Developer Name:");
        if (developer == null || developer.trim().isEmpty()) return;

        String taskName = JOptionPane.showInputDialog("Enter Task Name:");
        if (taskName == null || taskName.trim().isEmpty()) return;

        String durationStr = JOptionPane.showInputDialog("Enter Task Duration (hours):");
        if (durationStr == null) return;
        int duration;
        try {
            duration = Integer.parseInt(durationStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid duration! Please enter a number.");
            return;
        }

        String[] statusOptions = {"To Do", "Doing", "Done"};
        String status = (String) JOptionPane.showInputDialog(
            null, 
            "Select Task Status", 
            "Task Status", 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            statusOptions, 
            statusOptions[0]
        );
        if (status == null) return;

        // Generate unique task ID
        String taskID = UUID.randomUUID().toString().substring(0, 8);

        // Add to arrays
        developers.add(developer);
        taskNames.add(taskName);
        taskIDs.add(taskID);
        taskDurations.add(duration);
        taskStatuses.add(status);

        JOptionPane.showMessageDialog(null, "Task Added Successfully!\nTask ID: " + taskID);
    }

    // Method to display done tasks
    private static void displayDoneTasks() {
        StringBuilder doneTasks = new StringBuilder("Done Tasks:\n");
        boolean hasDoneTasks = false;

        for (int i = 0; i < taskStatuses.size(); i++) {
            if (taskStatuses.get(i).equals("Done")) {
                doneTasks.append("Developer: ").append(developers.get(i))
                    .append("\nTask Name: ").append(taskNames.get(i))
                    .append("\nDuration: ").append(taskDurations.get(i)).append(" hours\n\n");
                hasDoneTasks = true;
            }
        }

        if (!hasDoneTasks) {
            JOptionPane.showMessageDialog(null, "No completed tasks found.");
        } else {
            JOptionPane.showMessageDialog(null, doneTasks.toString());
        }
    }

    // Method to find the longest task
    private static void findLongestTask() {
        if (taskDurations.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tasks available.");
            return;
        }

        int longestIndex = 0;
        for (int i = 1; i < taskDurations.size(); i++) {
            if (taskDurations.get(i) > taskDurations.get(longestIndex)) {
                longestIndex = i;
            }
        }

        JOptionPane.showMessageDialog(null, 
            "Longest Task:\n" +
            "Developer: " + developers.get(longestIndex) + "\n" +
            "Duration: " + taskDurations.get(longestIndex) + " hours"
        );
    }

    // Method to search task by name
    private static void searchTaskByName() {
        String searchName = JOptionPane.showInputDialog("Enter Task Name to Search:");
        if (searchName == null) return;

        boolean found = false;
        for (int i = 0; i < taskNames.size(); i++) {
            if (taskNames.get(i).equalsIgnoreCase(searchName)) {
                JOptionPane.showMessageDialog(null, 
                    "Task Found:\n" +
                    "Task Name: " + taskNames.get(i) + "\n" +
                    "Developer: " + developers.get(i) + "\n" +
                    "Status: " + taskStatuses.get(i)
                );
                found = true;
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(null, "No task found with that name.");
        }
    }

    // Method to search tasks by developer
    private static void searchTasksByDeveloper() {
        String searchDeveloper = JOptionPane.showInputDialog("Enter Developer Name:");
        if (searchDeveloper == null) return;

        StringBuilder developerTasks = new StringBuilder("Tasks for " + searchDeveloper + ":\n");
        boolean found = false;

        for (int i = 0; i < developers.size(); i++) {
            if (developers.get(i).equalsIgnoreCase(searchDeveloper)) {
                developerTasks.append("Task Name: ").append(taskNames.get(i))
                    .append("\nStatus: ").append(taskStatuses.get(i)).append("\n\n");
                found = true;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(null, "No tasks found for this developer.");
        } else {
            JOptionPane.showMessageDialog(null, developerTasks.toString());
        }
    }

    // Method to delete a task
    private static void deleteTask() {
        String deleteTaskName = JOptionPane.showInputDialog("Enter Task Name to Delete:");
        if (deleteTaskName == null) return;

        boolean found = false;
        for (int i = 0; i < taskNames.size(); i++) {
            if (taskNames.get(i).equalsIgnoreCase(deleteTaskName)) {
                // Remove task from all arrays
                developers.remove(i);
                taskNames.remove(i);
                taskIDs.remove(i);
                taskDurations.remove(i);
                taskStatuses.remove(i);

                JOptionPane.showMessageDialog(null, "Task Deleted Successfully!");
                found = true;
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(null, "No task found with that name.");
        }
    }

    // Method to display full task report
    private static void displayFullTaskReport() {
        if (taskNames.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tasks to report.");
            return;
        }

        StringBuilder fullReport = new StringBuilder("Full Task Report:\n\n");
        for (int i = 0; i < taskNames.size(); i++) {
            fullReport.append("Task ID: ").append(taskIDs.get(i)).append("\n")
                .append("Developer: ").append(developers.get(i)).append("\n")
                .append("Task Name: ").append(taskNames.get(i)).append("\n")
                .append("Duration: ").append(taskDurations.get(i)).append(" hours\n")
                .append("Status: ").append(taskStatuses.get(i)).append("\n\n");
        }

        JOptionPane.showMessageDialog(null, fullReport.toString());
    }
}
}

    


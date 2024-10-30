/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poesecondpart;

import javax.swing.JOptionPane;

/**
 *
 * @author RC_Student_lab
 */
public class POESecondPart {

    public static void main(String[] args) {
        
        String firstname = JOptionPane.showInputDialog(null, "Please Enter your first name:");
        String lastname = JOptionPane.showInputDialog(null, "Please Enter your last name:");
        String username = JOptionPane.showInputDialog("Please Enter Username:");
        String password = JOptionPane.showInputDialog("Please Enter Password:");
        
         // Create an instance of Register with the username, password, first name, and last name
        Register register = new Register();

        // Simple login check
        if (!username.equals("Lef_2") || !password.equals("Mestalla@2023")) {
            JOptionPane.showMessageDialog(null, "Login Failed. Exiting.");
            System.exit(0);
        }

        JOptionPane.showMessageDialog(null, "Welcome to EasyKanban");

        int option;
        int totalTasks = 0;
        int totalHours = 0;

        do {
            // Display the main menu
            String menu = "1. Add Tasks\n2. Show Report\n3. Quit";
            option = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (option) {
                case 1:
                    // Add tasks
                    totalTasks = Integer.parseInt(JOptionPane.showInputDialog("Enter number of tasks to add:"));
                    Task[] tasks = new Task[totalTasks];

                    for (int i = 0; i < totalTasks; i++) {
                        String taskName = JOptionPane.showInputDialog("Enter Task Name:");
                        String taskDescription = JOptionPane.showInputDialog("Enter Task Description (Max 50 characters):");

                        // Validate task description length
                        while (taskDescription.length() > 50) {
                            JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters.");
                            taskDescription = JOptionPane.showInputDialog("Enter Task Description (Max 50 characters):");
                        }

                        String developerDetails = JOptionPane.showInputDialog("Enter Developer First and Last Name:");
                        int taskDuration = Integer.parseInt(JOptionPane.showInputDialog("Enter Task Duration (hours):"));

                        // Task status selection
                        String[] statuses = {"To Do", "Doing", "Done"};
                        String taskStatus = (String) JOptionPane.showInputDialog(null, "Select Task Status:", "Task Status",
                                JOptionPane.QUESTION_MESSAGE, null, statuses, statuses[0]);

                        // Create new Task
                        tasks[i] = new Task(taskName, i, taskDescription, developerDetails, taskDuration, taskStatus);
                        JOptionPane.showMessageDialog(null, "Task successfully captured");

                        // Display task details
                        JOptionPane.showMessageDialog(null, tasks[i].printTaskDetails());

                        // Accumulate total hours
                        totalHours += tasks[i].returnTotalHours();
                    }
                    JOptionPane.showMessageDialog(null, "Total hours for all tasks: " + totalHours);
                    break;

                case 2:
                    // Show Report (Coming Soon)
                    JOptionPane.showMessageDialog(null, "Coming Soon");
                    break;

                case 3:
                    // Quit the application
                    JOptionPane.showMessageDialog(null, "Exiting the application.");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid Option. Please select 1, 2, or 3.");
                    break;
            }
        } 
        while (option != 3);
    }
}

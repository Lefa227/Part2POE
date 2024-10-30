/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poesecondpart;

import java.util.Scanner;

/**
 *
 * @author RC_Student_lab
 */
public class Register {
 
    
    
    String firstname; // User's first name
    String lastname; // User's last name
    String password; // User's password
    String username; // User's username
    
    
  

    //Public access modifier to declare boolean
    public boolean correct = true;
    public boolean verify = false;
    public boolean check = false;

    public String getFirstname() {
        return firstname;
    }

    //Getters and setters to return the declared variables
    public String getLastname() {
        return lastname;
    }

    public String getsUsername() {
        return username;
    }

    public void setsUsername(String Username) {
        this.username = Username;
    }

    public String getsPassword() {
        return password;
    }

    public void setsPassword(String Password) {
        this.password = Password;
    }

    // Check if the username contains an underscore and is less than 5 characters 
    public boolean checkUsername() {
        if (username.length() <= 5 && username.contains("_")) {
            return true;
        }
        return false;
    }
    
    //Complexity requirements 
    // Check if the password contains a capital letter, a number and a special character
    //Make use of a loop
    public boolean checkPasswordComplexity() {
        boolean hasCapitalLetter = false;
        boolean hasNumber = false;
        boolean hasSpecialChar = false;

        if (password.length() >= 8) {
            for (char ch : password.toCharArray()) {
                if (Character.isUpperCase(ch)) {
                    hasCapitalLetter = true;
                } else if (Character.isDigit(ch)) {
                    hasNumber = true;
                } else if (!Character.isLetterOrDigit(ch)) {
                    hasSpecialChar = true;
                }
            }
        }
        return hasCapitalLetter && hasNumber && hasSpecialChar;
    }
    
    // Register the user by checking username and password validity
    public String registerUser() {
        if (checkUsername()) {
            System.out.println("Username successfully captured.");
        } else {
            System.out.println("Username is not correctly formatted. Please ensure that your Username contains an underscore and is no more than 5 characters in length.");
        }
        if (checkPasswordComplexity()) {
            System.out.println("Password successfully captured.");
        } else {
            System.out.println("Password is not correctly formatted. Please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.");
        }
        if (checkUsername() && checkPasswordComplexity()) {
            System.out.println("The two conditions in the above statement have been met and the user has been registered successfully.");
        } else {
            if (!checkPasswordComplexity()) {
                System.out.println("The Password does not meet the complexity requirements.");
            }
            if (!checkUsername()) {
                System.out.println("The username is not correctly formatted.");
            }
        }
        return "";
    }
    
     // Validate user login
    public boolean loginUser() {
     Scanner firstnameSc = new Scanner(System.in);
        System.out.println("Welcome user please enter your access details");

        //Make use of a do while loop
        System.out.println("Enter your username");
        String Username = firstnameSc.nextLine();

        System.out.println("Please enter your password");
        String password = firstnameSc.nextLine();

        //Make use of an if statement 
        if (Username.equals(this.username) && password.equals(this.password)) {
            correct = true;
        } else {
            System.out.println("USERNAME OR PASSWORD INCORRECT, PLEASE TRY AGAIN!");
            correct = false;

        }
        while (!correct);

        return correct;
    }
    
    
    }


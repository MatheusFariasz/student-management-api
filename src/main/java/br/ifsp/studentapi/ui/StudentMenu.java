package br.ifsp.studentapi.ui;

import br.ifsp.studentapi.dto.UpdateStudentInput;

import java.util.Scanner;

public class StudentMenu {
    private final Scanner scanner;

    public StudentMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showOptions(){
        System.out.println("\n=== MENU STUDENT ===");
        System.out.println("1 - Register");
        System.out.println("2 - Delete by name");
        System.out.println("3 - Update");
        System.out.println("4 - Search by name");
        System.out.println("5 - list all");
        System.out.println("6 - Exit");
        System.out.print("Choose an option: ");
    }

    public int readOption(){
        while (!scanner.hasNextInt()){
            scanner.nextLine();
            System.out.println("Invalid Option. Give a number: ");
        }
        int op = scanner.nextInt();
        scanner.nextLine();
        return op;
    }

    public String readLine(String label){
        System.out.println(label);
        return scanner.nextLine();
    }
}

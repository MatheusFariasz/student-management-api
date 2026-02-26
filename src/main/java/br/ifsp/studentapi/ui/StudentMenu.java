package br.ifsp.studentapi.ui;

import br.ifsp.studentapi.dto.UpdateStudentInput;
import br.ifsp.studentapi.model.Student;

import java.math.BigDecimal;
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

    public Student readCreateStudent(){
        System.out.println("STUDENT REGISTER: ");
        String name = readLine("name: ");
        String ra = readLine("ra: ");
        String email = readLine("email: ");
        BigDecimal grade1 = BigDecimal.valueOf(Long.parseLong(readLine("1° Grade: ")));
        BigDecimal grade2 = BigDecimal.valueOf(Long.parseLong(readLine("2° Grade: ")));
        BigDecimal grade3 = BigDecimal.valueOf(Long.parseLong(readLine("3° Grade: ")));

        return new Student(name, ra, email, grade1, grade2, grade3);
    }

    public UpdateStudentInput readUpdateStudent(){
        System.out.println("STUDENT UPDATE: ");

        String name = readLine("New name: ");
        String ra = readLine("New ra: ");
        String email = readLine("New email: ");
        BigDecimal grade1 = BigDecimal.valueOf(Long.parseLong(readLine("new 1° Grade: ")));
        BigDecimal grade2 = BigDecimal.valueOf(Long.parseLong(readLine("new 2° Grade: ")));
        BigDecimal grade3 = BigDecimal.valueOf(Long.parseLong(readLine("new 3° Grade: ")));

        return new UpdateStudentInput(name, ra, email, grade1, grade2, grade3);
    }

    public String deleteStudentInput(){
        System.out.println("DELETE STUDENT: ");

        return readLine("name: ");
    }

    public String readLine(String label){
        System.out.println(label);

        return scanner.nextLine();
    }
}

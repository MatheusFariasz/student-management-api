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
        System.out.println("5 - List all");
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
        BigDecimal grade1 = readGrade("1° Grade: ");
        BigDecimal grade2 = readGrade("2° Grade: ");
        BigDecimal grade3 = readGrade("3° Grade: ");

        return new Student(name, ra, email, grade1, grade2, grade3);
    }

    public UpdateStudentInput readUpdateStudent(){
        String name = readLine("New name: ");
        String ra = readLine("New ra: ");
        String email = readLine("New email: ");

        BigDecimal grade1 = readGrade("new 1° Grade: ");
        BigDecimal grade2 = readGrade("new 2° Grade: ");
        BigDecimal grade3 = readGrade("new 3° Grade: ");

        return new UpdateStudentInput(name, ra, email, grade1, grade2, grade3);
    }

    public String deleteStudentInput(){
        System.out.println("DELETE STUDENT: ");

        return readLine("name: ");
    }

    private BigDecimal readGrade(String label){
        BigDecimal grade;
        do{
            grade = readBigDecimalLine(label);
            if (grade.compareTo(BigDecimal.ZERO) < 0 || grade.compareTo(BigDecimal.TEN) > 0) {
                System.out.println("Invalid grade. Enter a value >= 0 and <= 10.");
            }
        } while (grade.compareTo(BigDecimal.ZERO) < 0 || grade.compareTo(BigDecimal.TEN) > 0);

        return grade;
    }

    private BigDecimal readBigDecimalLine(String label) {
        while (true) {
            System.out.print(label);
            String s = scanner.nextLine().trim().replace(",", ".");
            try {
                return new BigDecimal(s);
            } catch (NumberFormatException e) {
                System.out.println("Valid input exempla: 4,4 or 4.4");
            }
        }
    }

    private String readLine(String label){
        String input = null;

        do{
            System.out.println(label);
            input = scanner.nextLine();
            if (input == null || input.isBlank()) System.out.println(label + "can not be blank");
        }
        while (input == null || input.isBlank());

        return input;
    }
}

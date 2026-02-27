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
        System.out.println("\n=== CADASTRO DE ALUNOS ===");
        System.out.println("1 - Cadastrar Aluno");
        System.out.println("2 - Excluir Aluno");
        System.out.println("3 - Alterar  Aluno");
        System.out.println("4 - Buscar Aluno pelo nome");
        System.out.println("5 - Listar alunos (com status)");
        System.out.println("6 - Fim");
        System.out.print("Digite a opção desejada: ");
    }

    public int readOption(){
        while (!scanner.hasNextInt()){
            scanner.nextLine();
            System.out.println("Opção inválida! Digite um número: ");
        }
        int op = scanner.nextInt();
        scanner.nextLine();

        return op;
    }

    public Student readCreateStudent(){
        System.out.println("CADASTRO DE ALUNO: ");

        String name = readLine("Nome: ");
        String ra = readLine("RA: ");
        String email = readLine("Email: ");
        BigDecimal grade1 = readGrade("1° Nota: ");
        BigDecimal grade2 = readGrade("2° Nota: ");
        BigDecimal grade3 = readGrade("3° Nota: ");

        return new Student(name, ra, email, grade1, grade2, grade3);
    }

    public UpdateStudentInput readUpdateStudent(){
        String name = readLine("Novo nome: ");
        String ra = readLine("Novo RA: ");
        String email = readLine("Novo email: ");

        BigDecimal grade1 = readGrade("Nova 1° nota: ");
        BigDecimal grade2 = readGrade("Nova 2° nota: ");
        BigDecimal grade3 = readGrade("Nova 3° nota: ");

        return new UpdateStudentInput(name, ra, email, grade1, grade2, grade3);
    }

    public String deleteStudentInput(){
        System.out.println("EXCLUIR ALUNO: ");

        return readLine("Nome: ");
    }

    private BigDecimal readGrade(String label){
        BigDecimal grade;
        do{
            grade = readBigDecimalLine(label);
            if (grade.compareTo(BigDecimal.ZERO) < 0 || grade.compareTo(BigDecimal.TEN) > 0) {
                System.out.println("Nota inválida. Insira um valor >= 0 e <= 10.");
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
                System.out.println("Exemplo de entrada válido: 6,7 ou 6.7");
            }
        }
    }

    private String readLine(String label){
        String input = null;

        do{
            System.out.println(label);
            input = scanner.nextLine();
            if (input == null || input.isBlank()) System.out.println(label + "não pode ser vazio.");
        }
        while (input == null || input.isBlank());

        return input;
    }
}

package br.ifsp.studentapi;

import br.ifsp.studentapi.dao.StudentDAO;
import br.ifsp.studentapi.dto.UpdateStudentInput;
import br.ifsp.studentapi.model.Student;
import br.ifsp.studentapi.service.UpdateStudentService;
import br.ifsp.studentapi.ui.StudentMenu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Kaique Dias Galera e Matheus Gabriel Farias

        Scanner sc = new Scanner(System.in);
        StudentMenu menu = new StudentMenu(sc);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("studentapi");
        EntityManager em = factory.createEntityManager();

        StudentDAO studentDAO = new StudentDAO(em);
        UpdateStudentService updateStudentService = new UpdateStudentService(studentDAO);

        int op;
        do {
            menu.showOptions();
            op = menu.readOption();

            switch (op) {
                case 1 -> {
                    Student student = menu.readCreateStudent();

                    em.getTransaction().begin();
                    studentDAO.create(student);
                    em.getTransaction().commit();

                }
                case 2 -> {
                    String name = menu.deleteStudentInput();
                    em.getTransaction().begin();
                    boolean ok = studentDAO.deleteByName(name);
                    em.getTransaction().commit();
                    System.out.println(ok? "Aluno deletado!" : "Aluno não encontrado");
                }
                case 3 -> {
                    System.out.println("ALTERAR ALUNO: ");
                    System.out.println("Nome do aluno: ");
                    String name = sc.nextLine();

                    Student studentManaged = studentDAO.findByName(name);
                    if (studentManaged == null){
                        System.out.println("Aluno não encontrado");
                    }else {
                        UpdateStudentInput updateStudentInput = menu.readUpdateStudent();
                        em.getTransaction().begin();
                        boolean ok = updateStudentService.update(studentManaged, updateStudentInput);
                        System.out.println(ok ? "Aluno atualizado!" : "Aluno não encontrado");
                        em.getTransaction().commit();
                    }
                }
                case 4 -> {
                    System.out.println("BUSCAR ALUNO PELO NOME: ");
                    System.out.println("Nome do aluno: ");
                    String name = sc.nextLine();

                    Student student = studentDAO.findByName(name);
                    if (student == null) System.out.println("Aluno não encontrado");
                     else System.out.println(student.getStateAsString());
                }
                case 5 -> {
                    System.out.println("EXIBINDO TODOS OS ALUNOS: ");
                    List<Student> students = studentDAO.findAll();
                    for (Student s: students){
                        System.out.println(s);
                    }
                }
                case 6 ->{
                        em.close();
                        System.out.println("SAINDO...");
                }
                default -> System.out.println("OPÇÃO INVÁLIDA!");
            }
        } while (op != 6);
    }
}
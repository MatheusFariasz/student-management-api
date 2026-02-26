package br.ifsp.studentapi;

import br.ifsp.studentapi.dao.StudentDAO;
import br.ifsp.studentapi.dto.UpdateStudentInput;
import br.ifsp.studentapi.model.Student;
import br.ifsp.studentapi.service.UpdateStudentService;
import br.ifsp.studentapi.ui.StudentMenu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
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
                    System.out.println(ok? "Deleted" : "Student not find");
                }
                case 3 -> {
                    System.out.println("name: ");
                    String name = sc.nextLine();
                    UpdateStudentInput updateStudentInput = menu.readUpdateStudent();

                    em.getTransaction().begin();
                    updateStudentService.update(name, updateStudentInput);
                    em.getTransaction().commit();
                }
                case 4 -> {
                    System.out.println("Find student by name: ");
                    System.out.println("name: ");
                    String name = sc.nextLine();

                    Student student = studentDAO.findByName(name);
                    System.out.println(student.getStateAsString());
                }
                case 5 -> {
                    System.out.println("Showing all students: ");
                    List<Student> students = studentDAO.findAll();
                    for (Student s: students){
                        System.out.println(s);
                    }
                }
                case 6 ->{
                        em.close();
                        System.out.println("Leaving...");
                }
                default -> System.out.println("Invalid option.");
            }
        } while (op != 6);

//        Student student = new Student();
//        student.setName("Matheus");
//        student.setRa("SC304887X");
//        student.setEmail("matheus@ifsp.edu.br");
//        student.setGrade1(BigDecimal.valueOf(2));
//        student.setGrade2(BigDecimal.valueOf(9));
//        student.setGrade3(BigDecimal.valueOf(2));
//
//        System.out.println(student);
//
//
//
//
//
//        em.getTransaction().begin();
//
//        studentDAO.create(student);
//
//        UpdateStudentInput updateStudentInput = new UpdateStudentInput(
//                "kaique",
//                "SC3046699",
//                "kaique@email.com",
//                new BigDecimal(4),
//                new BigDecimal(2),
//                new BigDecimal(3.7));
//
//        updateStudentService.update("kaique", updateStudentInput);
//
//        em.getTransaction().commit();
//
//        em.close();

    }
}
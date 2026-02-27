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
                    System.out.println(ok? "Deleted" : "Student not found");
                }
                case 3 -> {
                    System.out.println("STUDENT UPDATE: ");
                    System.out.println("name: ");
                    String name = sc.nextLine();

                    Student studentManaged = studentDAO.findByName(name);
                    if (studentManaged == null){
                        System.out.println("Student not found");
                    }else {
                        UpdateStudentInput updateStudentInput = menu.readUpdateStudent();
                        em.getTransaction().begin();
                        boolean ok = updateStudentService.update(studentManaged, updateStudentInput);
                        System.out.println(ok ? "Updated" : "Student not found");
                        em.getTransaction().commit();
                    }
                }
                case 4 -> {
                    System.out.println("Find student by name: ");
                    System.out.println("name: ");
                    String name = sc.nextLine();

                    Student student = studentDAO.findByName(name);
                    if (student == null) System.out.println("Student not found");
                     else System.out.println(student.getStateAsString());
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
    }
}
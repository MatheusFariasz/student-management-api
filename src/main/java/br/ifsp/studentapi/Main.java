package br.ifsp.studentapi;

import br.ifsp.studentapi.dao.StudentDAO;
import br.ifsp.studentapi.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Student student = new Student();
        student.setName("Matheus");
        student.setRa("SC304887X");
        student.setEmail("matheus@ifsp.edu.br");
        student.setGrade1(BigDecimal.valueOf(2));
        student.setGrade2(BigDecimal.valueOf(9));
        student.setGrade3(BigDecimal.valueOf(2));

        System.out.println(student);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("studentapi");

        EntityManager em = factory.createEntityManager();

        StudentDAO studentDAO = new StudentDAO(em);

        em.getTransaction().begin();

        studentDAO.create(student);

        em.getTransaction().commit();

        em.close();

    }
}
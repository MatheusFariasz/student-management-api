package br.ifsp.studentapi.dao;

import br.ifsp.studentapi.model.Student;
import jakarta.persistence.EntityManager;

public class StudentDAO {
    private final EntityManager em;

    public StudentDAO(EntityManager em) {
        this.em = em;
    }

    public void create(Student student){
        em.persist(student);
    }
}

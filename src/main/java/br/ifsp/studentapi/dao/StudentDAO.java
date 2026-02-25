package br.ifsp.studentapi.dao;

import br.ifsp.studentapi.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.List;

public class StudentDAO {
    private final EntityManager em;

    public StudentDAO(EntityManager em) {
        this.em = em;
    }

    public void create(Student student){
        em.persist(student);
    }

    public void delete(String nome){
        Student student = em.find(Student.class, nome);
        if(student != null){
            em.remove(student);
        }
    }

    public void update(Student student){
        em.merge(student);
    }

    public Student findByName(String nome){
        String jpql = "SELECT s FROM Student s WHERE s.name = :n";
        try {
            return em.createQuery(jpql, Student.class)
                    .setParameter("n", nome)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Student> findAll(){
        String jpql = "select s from Student s ";
        return em.createQuery(jpql, Student.class).getResultList();
    }
}

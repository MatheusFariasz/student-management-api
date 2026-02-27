package br.ifsp.studentapi.service;

import br.ifsp.studentapi.dao.StudentDAO;
import br.ifsp.studentapi.dto.UpdateStudentInput;
import br.ifsp.studentapi.model.Student;

public class UpdateStudentService {
    private final StudentDAO studentDAO;

    public UpdateStudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public boolean update(Student studentManeged, UpdateStudentInput input){
        studentManeged.setName(input.name());
        studentManeged.setRa(input.ra());
        studentManeged.setEmail(input.email());
        studentManeged.setGrade1(input.grade1());
        studentManeged.setGrade2(input.grade2());
        studentManeged.setGrade3(input.grade3());

        return true;
    }


}

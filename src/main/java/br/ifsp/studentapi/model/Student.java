package br.ifsp.studentapi.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String ra;
    private String email;
    private BigDecimal grade1;
    private BigDecimal grade2;
    private BigDecimal grade3;

    public Student(){}

    public Student(String name, String ra, String email, BigDecimal grade1, BigDecimal grade2, BigDecimal grade3) {
        this.name = name;
        this.ra = ra;
        this.email = email;
        this.grade1 = grade1;
        this.grade2 = grade2;
        this.grade3 = grade3;
    }

    public void validateGrade(BigDecimal grade){
        if (grade.compareTo(BigDecimal.ZERO)< 0 || grade.compareTo(BigDecimal.TEN) < 0){
            throw new IllegalArgumentException("Invalid grade");
        }
    }

    public BigDecimal getGradeAveraged(){
        return grade1.add(grade2).add(grade3).divide(BigDecimal.valueOf(3), 2, RoundingMode.FLOOR);
    }

    public String getStatus(){
        if (getGradeAveraged().compareTo(BigDecimal.valueOf(4)) < 0) return "Failed";
        if (getGradeAveraged().compareTo(BigDecimal.valueOf(6)) < 0) return "Remedial";
        return "Approved";
    }

    public String getStateAsString(){
        return """
                -----------------------
                Student
                -----------------------
                Name: %s
                Email: %s
                RA: %s
                Grade: %s - %s - %s
                """.
                formatted(
                        name,
                        email,
                        ra,
                        grade1,
                        grade2,
                        grade3
                );
    }

    @Override
    public String toString() {
        return """
                -----------------------
                Student
                -----------------------
                Name: %s
                Email: %s
                RA: %s
                Grade: %s - %s - %s
                Average: %s
                Status: %s
                """.
                formatted(
                        name,
                        email,
                        ra,
                        grade1,
                        grade2,
                        grade3,
                        getGradeAveraged(),
                        getStatus()
                );

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getGrade1() {
        return grade1;
    }

    public void setGrade1(BigDecimal grade1) {
        validateGrade(grade1);
        this.grade1 = grade1;
    }

    public BigDecimal getGrade2() {
        return grade2;
    }

    public void setGrade2(BigDecimal grade2) {
        validateGrade(grade2);
        this.grade2 = grade2;
    }

    public BigDecimal getGrade3() {
        return grade3;
    }

    public void setGrade3(BigDecimal grade3) {
        validateGrade(grade3);
        this.grade3 = grade3;
    }
}

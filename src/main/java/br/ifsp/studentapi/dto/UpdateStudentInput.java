package br.ifsp.studentapi.dto;

import java.math.BigDecimal;

public record UpdateStudentInput(String name, String ra, String email, BigDecimal grade1, BigDecimal grade2, BigDecimal grade3) {
}

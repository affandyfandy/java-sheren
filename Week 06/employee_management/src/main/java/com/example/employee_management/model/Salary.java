package com.example.employee_management.model;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "salaries")
@Data
@NoArgsConstructor
public class Salary {
    
    @EmbeddedId
    private SalaryId id;

    @Column(name = "salary")
    private int salary;

    @Column(name = "to_date")
    private LocalDate toDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("empNo")
    @JoinColumn(name = "emp_no")
    @JsonIgnore
    private Employee employee;

    @Embeddable
    @Data
    @NoArgsConstructor
    public static class SalaryId {
        @Column(name = "emp_no")
        private Integer empNo;

        @Column(name = "from_date")
        private LocalDate fromDate;

        public SalaryId(Integer empNo, LocalDate fromDate) {
            this.empNo = empNo;
            this.fromDate = fromDate;
        }
    }
}

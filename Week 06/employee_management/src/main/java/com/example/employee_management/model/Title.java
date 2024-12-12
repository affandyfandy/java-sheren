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
@Table(name = "titles")
@Data
@NoArgsConstructor
public class Title {
    
    @EmbeddedId
    private TitleId id;

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
    public static class TitleId {

        @Column(name = "emp_no")
        private Integer empNo;

        @Column(name = "title")
        private String title;

        @Column(name = "from_date")
        private LocalDate fromDate;

        public TitleId(Integer empNo, String title, LocalDate fromDate) {
            this.empNo = empNo;
            this.title = title;
            this.fromDate = fromDate;
        }
        
    }
}

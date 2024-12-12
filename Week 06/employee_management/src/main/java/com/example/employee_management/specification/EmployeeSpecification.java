package com.example.employee_management.specification;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.data.jpa.domain.Specification;

import com.example.employee_management.model.Employee;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeSpecification implements Specification<Employee> {
    
    private SearchCriteria criteria;

    public EmployeeSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else if (root.get(criteria.getKey()).getJavaType() == LocalDate.class) {
                LocalDate date = LocalDate.parse(criteria.getValue().toString(), DateTimeFormatter.ISO_DATE);
                return builder.equal(root.get(criteria.getKey()), date);
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }
}

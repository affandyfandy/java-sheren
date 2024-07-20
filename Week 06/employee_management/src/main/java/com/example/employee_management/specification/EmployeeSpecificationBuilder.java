package com.example.employee_management.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.example.employee_management.model.Employee;

public class EmployeeSpecificationBuilder {

    private final List<SearchCriteria> params;

    public EmployeeSpecificationBuilder() {
        params = new ArrayList<>();
    }

    public EmployeeSpecificationBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Employee> build() {
        if (params.isEmpty()) {
            return null;
        }

        List<Specification<Employee>> specs = new ArrayList<>();
        for (SearchCriteria param : params) {
            specs.add(new EmployeeSpecification(param));
        }

        Specification<Employee> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specification.where(result).and(specs.get(i));
        }
        return result;
    }
}

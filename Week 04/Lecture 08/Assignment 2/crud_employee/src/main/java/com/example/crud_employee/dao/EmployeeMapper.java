package com.example.crud_employee.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.crud_employee.model.Employee;

public class EmployeeMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee emp = new Employee();
        emp.setId(rs.getString("id"));
        emp.setName(rs.getString("name"));
        emp.setDate_of_birth(rs.getDate("date_of_birth").toLocalDate());
        emp.setAddress(rs.getString("address"));
        emp.setDepartment(rs.getString("department"));
        return emp;
    }
}

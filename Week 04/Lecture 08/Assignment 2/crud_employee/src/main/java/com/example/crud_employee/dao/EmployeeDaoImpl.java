package com.example.crud_employee.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.crud_employee.model.Employee;

@Repository
public class EmployeeDaoImpl {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void setTemplate(JdbcTemplate template) {
        this.jdbcTemplate = template;
    }

    // See all employees
    public List<Employee> listEmployee() {
        String sql = "SELECT * FROM employee";
        return jdbcTemplate.query(sql, new EmployeeMapper());
    }

    // Get employee by id
    public Employee getEmployee(String id) {
        String sql = "SELECT * FROM `employee` WHERE `id` = ?";
        return jdbcTemplate.queryForObject(sql, new EmployeeMapper(), id);
    }

    // Add employee
    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO `employee` (`id`, `name`, `date_of_birth`, `address`, `department`) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql, employee.getId(), employee.getName(), employee.getDate_of_birth(), employee.getAddress(), employee.getDepartment());
    }

    // Update employee
    public void updateEmployee(Employee employee) {
        String sql = "UPDATE employee SET name = ?, date_of_birth = ?, address = ?, department = ? WHERE id = ?";
        jdbcTemplate.update(sql, employee.getName(), employee.getDate_of_birth(), employee.getAddress(), employee.getDepartment(), employee.getId());
    }

    // Delete employee
    public void deleteEmployee(Employee employee) {
        String sql = "DELETE FROM employee WHERE id = ?";
        jdbcTemplate.update(sql, employee.getId());
    }
}

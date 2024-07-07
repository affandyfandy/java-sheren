package com.example.crud_employee.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.crud_employee.model.Employee;

@Repository
public class EmployeeDaoImpl {

    @Autowired
    @Qualifier("jdbcTemplate1")
    private JdbcTemplate jdbcTemplate1;

    @Autowired
    @Qualifier("jdbcTemplate2")
    private JdbcTemplate jdbcTemplate2;

    // Methods for datasource 1
    public List<Employee> listEmployee1() {
        String sql = "SELECT * FROM employee";
        return jdbcTemplate1.query(sql, new EmployeeMapper());
    }

    public Employee getEmployee1(String id) {
        String sql = "SELECT * FROM employee WHERE id = ?";
        return jdbcTemplate1.queryForObject(sql, new EmployeeMapper(), id);
    }

    @Transactional(transactionManager = "dataSource1TransactionManager")
    public void addEmployee1(Employee employee) {
        String sql = "INSERT INTO employee (id, name, date_of_birth, address, department) VALUES (?,?,?,?,?)";
        jdbcTemplate1.update(sql, employee.getId(), employee.getName(), employee.getDate_of_birth(), employee.getAddress(), employee.getDepartment());
    }

    @Transactional(transactionManager = "dataSource1TransactionManager")
    public void updateEmployee1(Employee employee) {
        String sql = "UPDATE employee SET name = ?, date_of_birth = ?, address = ?, department = ? WHERE id = ?";
        jdbcTemplate1.update(sql, employee.getName(), employee.getDate_of_birth(), employee.getAddress(), employee.getDepartment(), employee.getId());
    }

    @Transactional(transactionManager = "dataSource1TransactionManager")
    public void deleteEmployee1(String id) {
        String sql = "DELETE FROM employee WHERE id = ?";
        jdbcTemplate1.update(sql, id);
    }

    // Methods for datasource 2
    public List<Employee> listEmployee2() {
        String sql = "SELECT * FROM employee";
        return jdbcTemplate2.query(sql, new EmployeeMapper());
    }

    public Employee getEmployee2(String id) {
        String sql = "SELECT * FROM employee WHERE id = ?";
        return jdbcTemplate2.queryForObject(sql, new EmployeeMapper(), id);
    }

    @Transactional(transactionManager = "dataSource2TransactionManager")
    public void addEmployee2(Employee employee) {
        String sql = "INSERT INTO employee (id, name, date_of_birth, address, department) VALUES (?,?,?,?,?)";
        jdbcTemplate2.update(sql, employee.getId(), employee.getName(), employee.getDate_of_birth(), employee.getAddress(), employee.getDepartment());
    }

    @Transactional(transactionManager = "dataSource2TransactionManager")
    public void updateEmployee2(Employee employee) {
        String sql = "UPDATE employee SET name = ?, date_of_birth = ?, address = ?, department = ? WHERE id = ?";
        jdbcTemplate2.update(sql, employee.getName(), employee.getDate_of_birth(), employee.getAddress(), employee.getDepartment(), employee.getId());
    }

    @Transactional(transactionManager = "dataSource2TransactionManager")
    public void deleteEmployee2(String id) {
        String sql = "DELETE FROM employee WHERE id = ?";
        jdbcTemplate2.update(sql, id);
    }
}

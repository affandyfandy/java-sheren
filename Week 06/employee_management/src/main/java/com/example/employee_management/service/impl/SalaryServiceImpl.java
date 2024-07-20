package com.example.employee_management.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee_management.model.Salary;
import com.example.employee_management.repository.SalaryRepository;
import com.example.employee_management.service.SalaryService;

@Service
public class SalaryServiceImpl implements SalaryService {
    
    private final SalaryRepository salaryRepository;

    @Autowired
    public SalaryServiceImpl(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }

    @Override
    public List<Salary> findAll() {
        return salaryRepository.findAll();
    }

    @Override
    public List<Salary> findByEmpNo(Integer empNo) {
        return salaryRepository.findByEmployeeEmpNo(empNo);
    }

    @Override
    public Salary findSalaryByEmpNoAndFromDate(Integer empNo, LocalDate fromDate) {
        Optional<Salary> salary = salaryRepository.findById(new Salary.SalaryId(empNo, fromDate));
        return salary.orElse(null);
    }

    @Override
    public Salary save(Salary salary) {
        return salaryRepository.save(salary);
    }

    @Override
    public void deleteSalary(Integer empNo, LocalDate fromDate) {
        salaryRepository.deleteById(new Salary.SalaryId(empNo, fromDate));
    }

}


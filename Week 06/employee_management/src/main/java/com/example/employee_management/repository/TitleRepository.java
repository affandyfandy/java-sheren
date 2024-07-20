package com.example.employee_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employee_management.model.Title;
import com.example.employee_management.model.Title.TitleId;

public interface TitleRepository extends JpaRepository<Title, TitleId>{
    List<Title> findByEmployeeEmpNo(Integer empNo);
}

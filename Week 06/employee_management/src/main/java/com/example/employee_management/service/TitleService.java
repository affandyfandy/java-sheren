package com.example.employee_management.service;

import java.time.LocalDate;
import java.util.List;

import com.example.employee_management.model.Title;

public interface TitleService {
    List<Title> findAll();
    List<Title> findByEmpNo(Integer empNo);
    Title findTitleByEmpNoAndTitleAndFromDate(Integer empNo, String title, LocalDate fromDate);
    Title save(Title title);
    void deleteTitle(Integer empNo, String title, LocalDate fromDate);
}

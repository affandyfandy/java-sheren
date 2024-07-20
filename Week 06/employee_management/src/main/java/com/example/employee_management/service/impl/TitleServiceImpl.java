package com.example.employee_management.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee_management.model.Title;
import com.example.employee_management.repository.TitleRepository;
import com.example.employee_management.service.TitleService;

@Service
public class TitleServiceImpl implements TitleService {
    
    private final TitleRepository titleRepository;

    @Autowired
    public TitleServiceImpl(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }

    @Override
    public List<Title> findAll() {
        return titleRepository.findAll();
    }

    @Override
    public List<Title> findByEmpNo(Integer empNo) {
        return titleRepository.findByEmployeeEmpNo(empNo);
    }

    @Override
    public Title findTitleByEmpNoAndTitleAndFromDate(Integer empNo, String title, LocalDate fromDate) {
        Optional<Title> titles = titleRepository.findById(new Title.TitleId(empNo, title, fromDate));
        return titles.orElse(null);
    }

    @Override
    public Title save(Title title) {
        return titleRepository.save(title);
    }

    @Override
    public void deleteTitle(Integer empNo, String title, LocalDate fromDate) {
        titleRepository.deleteById(new Title.TitleId(empNo, title, fromDate));
    }
}



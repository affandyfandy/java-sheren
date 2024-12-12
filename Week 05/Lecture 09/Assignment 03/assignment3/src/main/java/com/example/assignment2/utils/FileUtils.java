package com.example.assignment2.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.assignment2.model.Employee;

public class FileUtils {
    public static boolean hasCsvFormat(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (fileName != null && !fileName.isEmpty()) {
            String fileExtension = fileName.substring(fileName.lastIndexOf("."));
            return fileExtension.equalsIgnoreCase(".csv");
        }
        return false;
    }

    public static List<Employee> readEmployeesFromCSV(InputStream is) {
        List<Employee> employees = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            boolean isFirstLine = true; // Flag to skip the first line (header)
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip header line
                }
                String[] data = line.split(",");
                if (data.length > 0) {
                    Employee employee = new Employee();
                    employee.setId(data[0]);
                    employee.setName(data[1]);
                    employee.setDate_of_birth(LocalDate.parse(data[2], DateUtils.DATE_FORMATTER));
                    employee.setAddress(data[3]);
                    employee.setDepartment(data[4]);
                    employee.setSalary(Integer.parseInt(data[5]));
                    employees.add(employee);
                }
            }
        } catch (Exception e) {
        
        } 
        return employees;
    }

}
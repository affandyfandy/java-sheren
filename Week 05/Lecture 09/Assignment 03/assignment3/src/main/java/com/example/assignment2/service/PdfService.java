package com.example.assignment2.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.example.assignment2.model.Employee;

@Service
public class PdfService {
    
    @Autowired
    private TemplateEngine templateEngine;

    public InputStream generatePdf(List<Employee> employees) throws Exception {
        Map<String, Object> data = new HashMap<>();
        data.put("employees", employees);

        Employee highestSalaryEmployee = employees.stream().max(Comparator.comparingInt(Employee::getSalary)).orElse(null);
        Employee lowestSalaryEmployee = employees.stream().min(Comparator.comparingInt(Employee::getSalary)).orElse(null);
        int totalEmployees = employees.size();
        double avgSalary = employees.stream().mapToInt(Employee::getSalary).average().orElse(0.0);

        data.put("highestSalaryEmployee", highestSalaryEmployee);
        data.put("lowestSalaryEmployee", lowestSalaryEmployee);
        data.put("totalEmployees", totalEmployees);
        data.put("avgSalary", avgSalary);
        data.put("userName", "Sheren Yang");

        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        data.put("currentDate", currentDate);
        
        Context context = new Context();
        context.setVariables(data);

        String htmlContent = templateEngine.process("employees/pdf-template", context);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlContent);
        renderer.layout();
        renderer.createPDF(outputStream, false);
        renderer.finishPDF();

        return new ByteArrayInputStream(outputStream.toByteArray());
    }
}

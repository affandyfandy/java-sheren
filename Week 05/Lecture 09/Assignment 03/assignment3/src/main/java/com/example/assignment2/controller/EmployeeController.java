package com.example.assignment2.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.example.assignment2.model.Employee;
import com.example.assignment2.service.EmployeeService;
import com.example.assignment2.service.PdfService;
import com.example.assignment2.utils.FileUtils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping("/employees")
public class EmployeeController {
    
    private final EmployeeService employeeService;
    private final PdfService pdfService;
    
    @GetMapping("/list")
    public String listEmployees(Model theModel) {

        // Get the employees from db
        List<Employee> theEmployees = employeeService.findAll();

        // Add to the spring model
        theModel.addAttribute("employees", theEmployees);

        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFromForAdd(Model theModel) {

        //Create model attribute to bind form data
        Employee theEmployee = new Employee();

        theModel.addAttribute("employee", theEmployee);

        return "employees/employee-form";
    }

    @PostMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") String id,
                                    Model theModel) {

        // Get the employee from the service
        Employee theEmployee = employeeService.findById(id);

        // Set employee as a model attribute to pre-populate the form
        theModel.addAttribute("employee", theEmployee);

        // Send over to our form
        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {

        // Save the employee
        employeeService.save(theEmployee);

        // Use redirect to prevent duplicate submissions
        return "redirect:/employees/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("employeeId") String id) {

        // Delete the employee
        employeeService.deleteById(id);

        // Redirect to /employees/list"
        return "redirect:/employees/list";
    }

    @GetMapping("/uploadCsv")
    public String uploadCsvForm(Model theModel) {
        return "employees/upload-csv";
    }

    @PostMapping("/uploadCsv")
    public String uploadCsvFile(@RequestParam("csvFile") MultipartFile file) {
        if (FileUtils.hasCsvFormat(file)) {
            try {
                List<Employee> employees = FileUtils.readEmployeesFromCSV(file.getInputStream());
                employeeService.saveAll(employees);
                return "redirect:/employees/list";
            } catch (IOException e) {
                System.out.println("Error uploading CSV file!");
            }
        }
        System.out.println("Invalid file format or no file selected.");
        return "redirect:/employees/list";
    }

    @PostMapping("/pdf")
    public StreamingResponseBody generatePdf() throws Exception {
        List<Employee> employees = employeeService.findAll();
        return outputStream -> {
            try (InputStream is = pdfService.generatePdf(employees)) {
                byte[] buffer = new byte[2048];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            } catch (Exception ex) {
            }
        };
    }
}


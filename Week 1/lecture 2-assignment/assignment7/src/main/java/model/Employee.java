package model;

import java.time.LocalDate;

import utils.DateUtils;

public class Employee {
    private String id;
    private String name;
    private LocalDate dateOfBirth;
    private String address;
    private String department;

    public Employee(String id, String name, LocalDate dateOfBirth, String address, String department) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.department = department;
    }

    public static Employee dataCSV(String[] line) {
        String id = line[0];
        String name = line[1];
        LocalDate dob = DateUtils.parseDate(line[2]);
        String address = line[3];
        String department = line[4];
        return new Employee(id, name, dob, address, department);
    }

    @Override
    public String toString() {
        return id + "," + name + "," + DateUtils.formatDate(dateOfBirth) + "," + address + "," + department;
    }

    public String toCsvString() {
        return id + "," + name + "," + DateUtils.formatDate(dateOfBirth) + "," + address + "," + department;
    }

    // Getter and setter
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public String getDepartment() {
        return department;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
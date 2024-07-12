package com.example.employee_crud.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.employee_crud.dto.EmployeeDTO;
import com.example.employee_crud.model.Employee;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeDTO employeeToEmployeeDTO(Employee employee);

    Employee employeeDTOToEmployee(EmployeeDTO employeeDTO);
    
}

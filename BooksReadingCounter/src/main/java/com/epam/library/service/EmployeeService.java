package com.epam.library.service;

import java.util.List;

import com.epam.library.domain.Employee;
import com.epam.library.service.exception.ServiceException;

public interface EmployeeService {
	
	void addEmployee(Employee employee) throws ServiceException;

    void updateEmployee(Employee employee) throws ServiceException;

    void deleteEmployee(int id) throws ServiceException;

    Employee getEmployeeById(int id) throws ServiceException;

    List<Employee> getAllEmployees() throws ServiceException;
    
}

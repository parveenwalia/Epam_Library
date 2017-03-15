package com.epam.library.dao;

import java.util.List;

import com.epam.library.dao.exception.DAOException;
import com.epam.library.domain.Employee;

public interface EmployeeDAO {
	
	void addEmployee(Employee employee) throws DAOException;

    void updateEmployee(Employee employee) throws DAOException;

    void deleteEmployee(int id) throws DAOException;

    Employee getEmployeeById(int id) throws DAOException;

    List<Employee> getAllEmployees() throws DAOException;
    
}

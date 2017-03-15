package com.epam.library.service.impl;


import java.util.List;

import com.epam.library.dao.EmployeeDAO;
import com.epam.library.dao.exception.DAOException;
import com.epam.library.dao.factory.DAOFactory;
import com.epam.library.domain.Employee;
import com.epam.library.service.EmployeeService;
import com.epam.library.service.exception.ServiceException;

public class EmployeeServiceImpl implements EmployeeService{
	private final static String EMPTY_FIELD_ERROR="Empty field";
	private final static String WRONG_ID_ERROR="Wrong id";
	
	public void addEmployee(Employee employee) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getINSTANCE();
        EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();
        
        if(employee.getName()==null || employee.getName().equals("")){
        	throw new ServiceException(EMPTY_FIELD_ERROR);
        }
        
        if(employee.getEmail()==null || employee.getEmail().equals("")){
        	throw new ServiceException(EMPTY_FIELD_ERROR);
        }
        
        try {
            employeeDAO.addEmployee(employee);
        } 
        catch (DAOException e){
            throw new ServiceException(e);
        }
		
	}

	public void updateEmployee(Employee employee) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getINSTANCE();
        EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();
        
        if(employee.getId()<0){
        	throw new ServiceException(WRONG_ID_ERROR);
        }
        
        if(employee.getName()==null || employee.getName().equals("")){
        	throw new ServiceException(EMPTY_FIELD_ERROR);
        }
        
        if(employee.getEmail()==null || employee.getEmail().equals("")){
        	throw new ServiceException(EMPTY_FIELD_ERROR);
        }
        
        try {
            employeeDAO.updateEmployee(employee);
        } 
        catch (DAOException e){
            throw new ServiceException(e);
        }
		
	}

	public void deleteEmployee(int id) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getINSTANCE();
        EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();
        
        if(id<0){
        	throw new ServiceException(WRONG_ID_ERROR);
        }
        
        try {
            employeeDAO.deleteEmployee(id);
        } 
        catch (DAOException e){
            throw new ServiceException(e);
        }
		
	}

	public Employee getEmployeeById(int id) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getINSTANCE();
        EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();
        
        Employee employee;
        
        if(id<0){
        	throw new ServiceException(WRONG_ID_ERROR);
        }
        
        try {
            employee = employeeDAO.getEmployeeById(id);
        } 
        catch (DAOException e){
            throw new ServiceException(e);
        }

        return employee;
	}

	public List<Employee> getAllEmployees() throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getINSTANCE();
        EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();
        List<Employee> emploeesList;

        try {
            emploeesList = employeeDAO.getAllEmployees();
        } 
        catch (DAOException e){
            throw new ServiceException(e);
        }

        return emploeesList;
	}

}

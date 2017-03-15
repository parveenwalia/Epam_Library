package com.epam.library.dao;

import java.util.List;
import java.util.Map;

import com.epam.library.dao.exception.DAOException;
import com.epam.library.domain.Book;
import com.epam.library.domain.Employee;

public interface StatisticDAO {
	
	Map<String, Integer> getBooksReadMoreThenOne() throws DAOException;
	
	Map<Employee, Integer> getBooksReadLessThenTwo() throws DAOException;
    
}

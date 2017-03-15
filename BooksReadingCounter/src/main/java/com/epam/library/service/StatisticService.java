package com.epam.library.service;

import java.util.List;
import java.util.Map;

import com.epam.library.domain.Book;
import com.epam.library.domain.Employee;
import com.epam.library.service.exception.ServiceException;

public interface StatisticService {
	
	Map<String, Integer> getBooksReadMoreThenOne() throws ServiceException;

	Map<Employee, Integer> getBooksReadLessThenTwo() throws ServiceException;
    
}

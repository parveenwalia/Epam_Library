package com.epam.library.service.impl;

import java.util.List;
import java.util.Map;

import com.epam.library.dao.StatisticDAO;
import com.epam.library.dao.exception.DAOException;
import com.epam.library.dao.factory.DAOFactory;
import com.epam.library.domain.Book;
import com.epam.library.domain.Employee;
import com.epam.library.service.StatisticService;
import com.epam.library.service.exception.ServiceException;

public class StatisticServiceImpl implements StatisticService{
	
	public Map<String, Integer> getBooksReadMoreThenOne() throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getINSTANCE();
        StatisticDAO statisticDAO = daoFactory.getStatisticDAO();

        Map<String, Integer> result;
        
        try {
            result = statisticDAO.getBooksReadMoreThenOne();
        }
        catch (DAOException e){
            throw new ServiceException(e);
        }

        return result;
	}

	public Map<Employee, Integer> getBooksReadLessThenTwo() throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getINSTANCE();
        StatisticDAO statisticDAO = daoFactory.getStatisticDAO();

        Map<Employee, Integer> result;
        
        try {
            result = statisticDAO.getBooksReadLessThenTwo();
        } 
        catch (DAOException e){
            throw new ServiceException(e);
        }

        return result;
	}

}

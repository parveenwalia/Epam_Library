package com.epam.library.dao.factory;

import com.epam.library.dao.BookDAO;
import com.epam.library.dao.EmployeeDAO;
import com.epam.library.dao.StatisticDAO;
import com.epam.library.dao.impl.BookDAOProvider;
import com.epam.library.dao.impl.EmployeeDAOProvider;
import com.epam.library.dao.impl.StatisticDAOProvider;

public class DAOFactory {
	private static final DAOFactory INSTANCE = new DAOFactory();

    private BookDAO bookDAO = new BookDAOProvider();
    private EmployeeDAO employeeDAO = new EmployeeDAOProvider();
    private StatisticDAO statisticDAO = new StatisticDAOProvider();
    
    private DAOFactory(){}

    public static DAOFactory getINSTANCE() {
        return INSTANCE;
    }

    public BookDAO getBookDAO() {
        return bookDAO;
    }

    public EmployeeDAO getEmployeeDAO() {
        return employeeDAO;
    }

    public StatisticDAO getStatisticDAO() {
        return statisticDAO;
    }
    
}

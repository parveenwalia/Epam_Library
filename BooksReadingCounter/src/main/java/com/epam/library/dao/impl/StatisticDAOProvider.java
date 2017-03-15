package com.epam.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.epam.library.dao.StatisticDAO;
import com.epam.library.dao.connection.ConnectionPool;
import com.epam.library.dao.exception.DAOException;
import com.epam.library.domain.Book;
import com.epam.library.domain.Employee;

public class StatisticDAOProvider implements StatisticDAO{
	private static final String SELECT_READER_WHO_READ_LOT = "SELECT employee.name, coalesce(COUNT(employee_book.employee_id),0) AS book_number\n" +
    "FROM epam_library.employee_book \n" +
    "JOIN epam_library.employee ON employee.id=employee_book.employee_id\n" +
    "GROUP BY employee_id HAVING book_number>1 ORDER BY book_number;";
	
	 private static final String SELECT_READERS_WHO_DONT_READ_LOT = "SELECT employee.name, employee.date_of_birth, coalesce(count(employee_book.employee_id), 0) AS book_number\n" +
     "FROM epam_library.employee_book \n" +
     "RIGHT JOIN epam_library.employee ON employee.id=employee_book.employee_id\n" +
     "GROUP BY employee_id HAVING book_number<=2 ORDER BY date_of_birth, book_number DESC;";
	 
	 private final static String ERROR="PreparedStatement closing error";
	 public static final Logger logger = Logger.getLogger(StatisticDAOProvider.class);
	 
	public Map<String, Integer> getBooksReadMoreThenOne() throws DAOException {
		ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        Map<String, Integer> readers = new HashMap<String,Integer>();
        PreparedStatement st=null;
        ResultSet set=null;

        try{
        	
            con=conPool.retrieve();
            
            st=con.prepareStatement(SELECT_READER_WHO_READ_LOT);
            set=st.executeQuery();
            
            while(set.next()){
            	readers.put(set.getString(1), set.getInt(2));
            }

            return readers;

        }

        catch(SQLException e){
            throw new DAOException(e);
        }

        catch(InterruptedException e){
            throw new DAOException(e);
        }

        finally{

            try {
                st.close();
            } 
            catch (SQLException e) {
                logger.error(ERROR);
            }

            conPool.putback(con); 
        }
        
	}

	public Map<Employee, Integer> getBooksReadLessThenTwo() throws DAOException {
		ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        Map<Employee, Integer> readers = new HashMap<Employee,Integer>();
        PreparedStatement st=null;
        ResultSet set=null;
        Employee employee=null;
        
        try{
        	
            con=conPool.retrieve();
            
            st=con.prepareStatement(SELECT_READERS_WHO_DONT_READ_LOT);
            set=st.executeQuery();
            
            while(set.next()){
            	employee=new Employee();
            	employee.setName(set.getString(1));
            	employee.setBirthday(set.getDate(2));
            	readers.put(employee, set.getInt(3));
            }

            return readers;

        }

        catch(SQLException e){
            throw new DAOException(e);
        }

        catch(InterruptedException e){
            throw new DAOException(e);
        }

        finally{

            try {
                st.close();
            } 
            catch (SQLException e) {
                logger.error(ERROR);
            }

            conPool.putback(con); 
        }
	}

}

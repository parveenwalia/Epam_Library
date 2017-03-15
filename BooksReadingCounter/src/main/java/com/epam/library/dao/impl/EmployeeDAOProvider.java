package com.epam.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.library.dao.EmployeeDAO;
import com.epam.library.dao.connection.ConnectionPool;
import com.epam.library.dao.exception.DAOException;
import com.epam.library.domain.Employee;

public class EmployeeDAOProvider implements EmployeeDAO{
	private final static String SELECT_EMPLOYEES_QUERY="SELECT `id`,`name`,`email`,`date_of_birth` FROM `epam_library`.`employee`";
    private final static String SELECT_EMPLOYEE_BY_ID_QUERY="SELECT `id`,`name`,`email`,`date_of_birth` FROM `epam_library`.`employee` WHERE `id`=?";
    private final static String EDIT_EMPLOYEE_QUERY="UPDATE `epam_library`.`employee` SET `name`=?,`email`=?,`date_of_birth`=? WHERE `id`=?";
    private final static String DELETE_EMPLOYEE_QUERY="DELETE FROM `epam_library`.`employee` WHERE `id`=?";
    private final static String ADD_EMPLOYEE_QUERY="INSERT INTO `epam_library`.`employee` (`name`, `email`, `date_of_birth`) VALUES (?,?,?)";
    private final static String ERROR="PreparedStatement closing error";
    public static final Logger logger = Logger.getLogger(EmployeeDAOProvider.class);
    
	public void addEmployee(Employee employee) throws DAOException {
		ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        PreparedStatement st=null;

        try{
            con=conPool.retrieve();    
            st=con.prepareStatement(ADD_EMPLOYEE_QUERY);

            st.setString(1, employee.getName());
            st.setString(2, employee.getEmail());
            st.setDate(3, employee.getBirthday());
            

            st.executeUpdate();

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

	public void updateEmployee(Employee employee) throws DAOException {
		ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        PreparedStatement st=null;

        try{
            con=conPool.retrieve();    
            st=con.prepareStatement(EDIT_EMPLOYEE_QUERY);
            
            st.setString(1, employee.getName());
            st.setString(2, employee.getEmail());
            st.setDate(3, employee.getBirthday());
            st.setInt(4, employee.getId());

            st.executeUpdate();
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

	public void deleteEmployee(int id) throws DAOException {
		ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        PreparedStatement st=null;

        try{
            con=conPool.retrieve();    
            st=con.prepareStatement(DELETE_EMPLOYEE_QUERY);
    
            st.setInt(1, id);

            st.executeUpdate();
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

	public Employee getEmployeeById(int id) throws DAOException {
		ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        Employee employee=new Employee();
        PreparedStatement st=null;
        ResultSet set=null;

        try{
            con=conPool.retrieve();    
            st=con.prepareStatement(SELECT_EMPLOYEE_BY_ID_QUERY);

            st.setInt(1, id);

            set=st.executeQuery();
            
            if(set.next()){
                employee.setId(set.getInt(1));
                employee.setName(set.getString(2));
                employee.setEmail(set.getString(3));
                employee.setBirthday(set.getDate(4));
            }
            else{
                throw new DAOException();
            }

            return employee;
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

	public List<Employee> getAllEmployees() throws DAOException {
		ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        List<Employee> list=new ArrayList<Employee>();
        Employee employee=null;
        PreparedStatement st=null;
        ResultSet set=null;

        try{
            con=conPool.retrieve();
            st=con.prepareStatement(SELECT_EMPLOYEES_QUERY);
            set=st.executeQuery();

            while(set.next()){
                employee=new Employee();
                employee.setId(set.getInt(1));
                employee.setName(set.getString(2));
                employee.setEmail(set.getString(3));
                employee.setBirthday(set.getDate(4));
                list.add(employee);
            }

            return list;

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

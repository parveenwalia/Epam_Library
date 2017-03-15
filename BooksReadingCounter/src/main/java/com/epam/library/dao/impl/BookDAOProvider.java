package com.epam.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.library.dao.BookDAO;
import com.epam.library.dao.connection.ConnectionPool;
import com.epam.library.dao.exception.DAOException;
import com.epam.library.domain.Book;

public class BookDAOProvider implements BookDAO{
	private final static String SELECT_BOOKS_QUERY="SELECT `id`,`title`,`author`,`brief`,`publish_year` FROM `epam_library`.`book`";
    private final static String SELECT_BOOK_BY_ID_QUERY="SELECT `id`,`title`,`author`,`brief`,`publish_year` FROM `epam_library`.`book` WHERE `id`=?";
    private final static String EDIT_BOOK_QUERY="UPDATE `epam_library`.`book` SET `title`=?,`author`=?,`brief`=?,`publish_year`=? WHERE `id`=?";
    private final static String RENAME_BOOK_QUERY="UPDATE `epam_library`.`book` SET `title`=? WHERE `title`=?";
    private final static String DELETE_BOOK_QUERY="DELETE FROM `epam_library`.`book` WHERE `id`=?";
    private final static String ADD_BOOK_QUERY="INSERT INTO `epam_library`.`book`(`title`, `author`, `brief`, `publish_year`) VALUES(?,?,?,?)";
    private final static String ERROR="PreparedStatement closing error";
    public static final Logger logger = Logger.getLogger(BookDAOProvider.class);
    
	public void addBook(Book book) throws DAOException {
		ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        PreparedStatement st=null;
        
        try{
            con=conPool.retrieve();    
            st=con.prepareStatement(ADD_BOOK_QUERY);

            st.setString(1, book.getTitle());
            st.setString(2, book.getAuthor());
            st.setString(3, book.getBrief());
            st.setInt(4, book.getPublishYear());
            

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

	public void updateBook(Book book) throws DAOException {
		ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        PreparedStatement st=null;

        try{
            con=conPool.retrieve();    
            st=con.prepareStatement(EDIT_BOOK_QUERY);
            
            st.setString(1, book.getTitle());
            st.setString(2, book.getAuthor());
            st.setString(3, book.getBrief());
            st.setInt(4, book.getPublishYear());
            st.setInt(5, book.getId());

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

	public void deleteBook(int id) throws DAOException {
		ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        PreparedStatement st=null;

        try{
            con=conPool.retrieve();    
            st=con.prepareStatement(DELETE_BOOK_QUERY);
    
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

	public Book getBookById(int id) throws DAOException {
		ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        Book book=new Book();
        PreparedStatement st=null;
        ResultSet set=null;

        try{
            con=conPool.retrieve();    
            st=con.prepareStatement(SELECT_BOOK_BY_ID_QUERY);

            st.setInt(1, id);
            set=st.executeQuery();

            if(set.next()){
                book.setId(set.getInt(1));
                book.setTitle(set.getString(2));
                book.setAuthor(set.getString(3));
                book.setBrief(set.getString(4));
                book.setPublishYear(set.getInt(5));
            }
            else{
                throw new DAOException();
            }

            return book;
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

	public List<Book> getAllBooks() throws DAOException {
		ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        List<Book> list=new ArrayList<Book>();
        Book book=null;
        PreparedStatement st=null;
        ResultSet set=null;

        try{
        	
            con=conPool.retrieve();
            
            st=con.prepareStatement(SELECT_BOOKS_QUERY);
            set=st.executeQuery();
            
            while(set.next()){
                book=new Book();
                book.setId(set.getInt(1));
                book.setTitle(set.getString(2));
                book.setAuthor(set.getString(3));
                book.setBrief(set.getString(4));
                book.setPublishYear(set.getInt(5));
                list.add(book);
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

	public void renameBookByTitle(String oldTitle,String newTitle) throws DAOException {
		ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        PreparedStatement st=null;

        try{
            con=conPool.retrieve();    
            st=con.prepareStatement(RENAME_BOOK_QUERY);
            
            st.setString(1, newTitle);
            st.setString(2, oldTitle);

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
    
}

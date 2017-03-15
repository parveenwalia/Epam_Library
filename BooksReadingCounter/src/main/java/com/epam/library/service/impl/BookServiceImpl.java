package com.epam.library.service.impl;

import java.util.List;

import com.epam.library.dao.BookDAO;
import com.epam.library.dao.exception.DAOException;
import com.epam.library.dao.factory.DAOFactory;
import com.epam.library.domain.Book;
import com.epam.library.service.BookService;
import com.epam.library.service.exception.ServiceException;

public class BookServiceImpl implements BookService {
	private final static String EMPTY_FIELD_ERROR="Empty field";
	private final static String WRONG_ID_ERROR="Wrong id";
	
    public void addBook(Book book) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getINSTANCE();
        BookDAO bookDAO = daoFactory.getBookDAO();
            
        if(book.getAuthor()==null || book.getAuthor().equals("")){
        	throw new ServiceException(EMPTY_FIELD_ERROR);
        }
        
        if(book.getBrief()==null || book.getBrief().equals("")){
        	throw new ServiceException(EMPTY_FIELD_ERROR);
        }
        
        try {
            bookDAO.addBook(book);
        } 
        catch (DAOException e){
            throw new ServiceException(e);
        }

    }

    public void updateBook(Book book) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getINSTANCE();
        BookDAO bookDAO = daoFactory.getBookDAO();
        
        if(book.getId()<0){
        	throw new ServiceException(WRONG_ID_ERROR);
        }
        
        if(book.getAuthor()==null || book.getAuthor().equals("")){
        	throw new ServiceException(EMPTY_FIELD_ERROR);
        }
        
        if(book.getBrief()==null || book.getBrief().equals("")){
        	throw new ServiceException(EMPTY_FIELD_ERROR);
        }
        
        try {
            bookDAO.updateBook(book);
        }
        catch (DAOException e){
            throw new ServiceException(e);
        }

    }

    public void deleteBook(int id) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getINSTANCE();
        BookDAO bookDAO = daoFactory.getBookDAO();
        
        if(id<0){
        	throw new ServiceException(WRONG_ID_ERROR);
        }
        
        try {
            bookDAO.deleteBook(id);
        } 
        catch (DAOException e){
            throw new ServiceException(e);
        }

    }

    public Book getBookById(int id) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getINSTANCE();
        BookDAO bookDAO = daoFactory.getBookDAO();

        Book book;
        
        if(id<0){
        	throw new ServiceException(WRONG_ID_ERROR);
        }
        
        try {
            book = bookDAO.getBookById(id);
        } 
        catch (DAOException e){
            throw new ServiceException(e);

        }

        return book;

    }

    public List<Book> getAllBooks() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getINSTANCE();
        BookDAO bookDAO = daoFactory.getBookDAO();

        List<Book> bookList;

        try {
            bookList = bookDAO.getAllBooks();
        } 
        catch (DAOException e){
            throw new ServiceException(e);
        }

        return bookList;

    }



    public void renameBookByTitle(String oldTitle,String newTitle) throws ServiceException {
           
        if (oldTitle==null || oldTitle.equals("")){
        	throw new ServiceException(EMPTY_FIELD_ERROR);
        }
        
        if (newTitle==null || newTitle.equals("")){
        	throw new ServiceException(EMPTY_FIELD_ERROR);
        }

        DAOFactory daoFactory = DAOFactory.getINSTANCE();
        BookDAO bookDAO = daoFactory.getBookDAO();

        try {
            bookDAO.renameBookByTitle(oldTitle,newTitle);
        } 
        catch (DAOException e){
            throw new ServiceException(e);
        }

    }


}

package com.epam.library.dao;

import java.util.List;

import com.epam.library.dao.exception.DAOException;
import com.epam.library.domain.Book;

public interface BookDAO {
	
	void addBook(Book book) throws DAOException;

    void updateBook(Book book) throws DAOException;

    void deleteBook(int id) throws DAOException;

    Book getBookById(int id) throws DAOException;

    List<Book> getAllBooks() throws DAOException;

    void renameBookByTitle(String oldTitle,String newTitle) throws DAOException;
    
}

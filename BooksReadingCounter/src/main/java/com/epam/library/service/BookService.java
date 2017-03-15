package com.epam.library.service;

import java.util.List;

import com.epam.library.domain.Book;
import com.epam.library.service.exception.ServiceException;

public interface BookService {
	
	void addBook(Book book) throws ServiceException;

    void updateBook(Book book) throws ServiceException;

    void deleteBook(int id) throws ServiceException;

    Book getBookById(int id) throws ServiceException;

    List<Book> getAllBooks() throws ServiceException;

    void renameBookByTitle(String oldTitle,String newTitle) throws ServiceException;
    
}

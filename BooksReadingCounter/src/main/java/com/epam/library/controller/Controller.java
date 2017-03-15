package com.epam.library.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.epam.library.dao.connection.ConnectionPool;
import com.epam.library.dao.impl.BookDAOProvider;
import com.epam.library.domain.Book;
import com.epam.library.domain.Employee;
import com.epam.library.service.BookService;
import com.epam.library.service.StatisticService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

public class Controller {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static final Logger logger = Logger.getLogger(Controller.class);
    private final static String READING_ERROR="Reading error";
    private final static String GETTING_BOOKS_ERROR="Getting books error";
    private final static String GETTING_BOOK_ERROR="Getting book error";
    private final static String DELETE_BOOK_ERROR="Delete book error";
    private final static String UPDATE_BOOK_ERROR="Update book error";
    private final static String RENAME_BOOK_ERROR="Rename book error";
    private final static String ADD_BOOK_ERROR="Add book error";
    private final static String GETTING_GOOD_READERS_ERROR="Getting good readers error";
    private final static String GETTING_BAD_READERS_ERROR="Getting bad readers error";
    private final static String CONNECTION_POOL_INIT_ERROR="Connection pool init error";
    
	public static void main(String[] args) {
		String i = null;
		int number;
        boolean flag = true;
        ServiceFactory factory=ServiceFactory.getINSTANCE();
        BookService bookService=factory.getBookService();
        StatisticService statisticService=factory.getStatisticService();
        String strId=null;
        int id=-1;
        String title=null;
        String author=null;
        String brief=null;
        String strPublishYear=null;
        int publishYear=-1;
        Book book=null;
        Map<String,Integer> goodReaders=null;
        Map<Employee,Integer> badReaders=null;
        String oldTitle=null;
        String newTitle=null;
     
        connectionPoolInit();
        
        while (flag) {
            System.out.println("Available options:");
            System.out.println("1 - see all books");
            System.out.println("2 - see book by id");
            System.out.println("3 - add book");
            System.out.println("4 - update book");
            System.out.println("5 - rename book");
            System.out.println("6 - delete book");
            System.out.println("7 - get good readers");
            System.out.println("8 - get bad readers");
            System.out.println("Other - exit program");
            System.out.println("Please input desired value");
            
            try {
				i = reader.readLine();
			} 
            catch (IOException e) {
            	logger.error(READING_ERROR);
			}
            
            number=Integer.valueOf(i);


            System.out.println(i);

            switch (number) {

                case 1:
                    
				    List<Book> books=null;
				    
					try {
						books = bookService.getAllBooks();
					} 
					catch (ServiceException e) {
						logger.error(GETTING_BOOKS_ERROR);
					}
                    
                    for(Book singleBook:books){
                    	System.out.println("id-"+singleBook.getId());
                    	System.out.println("title-"+singleBook.getTitle());
                    	System.out.println("author-"+singleBook.getAuthor());
                    	System.out.println("brief-"+singleBook.getBrief());
                    	System.out.println("publish year-"+singleBook.getPublishYear());
                    }
                    
                    break;

                case 2:
                	
                	System.out.println("Input id");
                	
					try {
						strId=reader.readLine();
					} 
					catch (IOException e) {
						logger.error(READING_ERROR);
					}
					
                    id=Integer.valueOf(strId);
                    
					try {
						book=bookService.getBookById(id);
					} 
					catch (ServiceException e) {
						logger.error(GETTING_BOOK_ERROR);
					}
                    
                    System.out.println("id-"+book.getId());
                	System.out.println("title-"+book.getTitle());
                	System.out.println("author-"+book.getAuthor());
                	System.out.println("brief-"+book.getBrief());
                	System.out.println("publish year-"+book.getPublishYear());
                	
                    break;

                case 3:
                    
                	System.out.println("Input title");
                	
					try {
						title=reader.readLine();
					} 
					catch (IOException e) {
						logger.error(READING_ERROR);
					}
					
					System.out.println("Input author");
					
					try {
						author=reader.readLine();
					} 
					catch (IOException e) {
						logger.error(READING_ERROR);
					}
					
					System.out.println("Input brief");
					
					try {
						brief=reader.readLine();
					} 
					catch (IOException e) {
						logger.error(READING_ERROR);
					}
					
					System.out.println("Input publish year");
					
					try {
						strPublishYear=reader.readLine();
					} 
					catch (IOException e) {
						logger.error(READING_ERROR);
					}
					
                    publishYear=Integer.valueOf(strPublishYear);
                    
                    book=new Book();
                    book.setId(id);
                    book.setAuthor(author);
                    book.setBrief(brief);
                    book.setPublishYear(publishYear);
                    book.setTitle(title);
                    
					try {
						bookService.addBook(book);
					} 
					catch (ServiceException e) {
						logger.error(ADD_BOOK_ERROR);
					}
                    
                    break;
                case 4:
                    
                	System.out.println("Input id");
                	
					try {
						strId=reader.readLine();
					} 
					catch (IOException e) {
						logger.error(READING_ERROR);
					}
					
                    id=Integer.valueOf(strId);
                    
                    System.out.println("Input title");
                    
					try {
						title=reader.readLine();
					} 
					catch (IOException e) {
						logger.error(READING_ERROR);
					}
					
					System.out.println("Input author");
					
					try {
						author=reader.readLine();
					}
					catch (IOException e) {
						logger.error(READING_ERROR);
					}
					
					System.out.println("Input brief");
					
					try {
						brief=reader.readLine();
					} 
					catch (IOException e) {
						logger.error(READING_ERROR);
					}
					
					System.out.println("Input publish year");
					
					try {
						strPublishYear=reader.readLine();
					} 
					catch (IOException e) {
						logger.error(READING_ERROR);
					}
					
                    publishYear=Integer.valueOf(strPublishYear);
                    
                    book=new Book();
                    book.setId(id);
                    book.setAuthor(author);
                    book.setBrief(brief);
                    book.setPublishYear(publishYear);
                    book.setTitle(title);
                    
					try {
						bookService.updateBook(book);
					}
					catch (ServiceException e) {
						logger.error(UPDATE_BOOK_ERROR);
					}
                    
                    break;
                case 5:

                	System.out.println("Input oldTitle");
                	
					try {
						oldTitle=reader.readLine();
					} 
					catch (IOException e) {
						logger.error(READING_ERROR);
					}
                    
                    System.out.println("Input newTitle");
                    
					try {
						newTitle=reader.readLine();
					} 
					catch (IOException e) {
						logger.error(READING_ERROR);
					}
                                  
					try {
						bookService.renameBookByTitle(oldTitle, newTitle);
					} 
					catch (ServiceException e) {
						logger.error(RENAME_BOOK_ERROR);
					}
                    
                    break;
                case 6:
                    
                	System.out.println("Input id");
                	
					try {
						strId=reader.readLine();
					} 
					catch (IOException e) {
						logger.error(READING_ERROR);
					}
                    id=Integer.valueOf(strId);
                                  
					try {
						bookService.deleteBook(id);
					}
					catch (ServiceException e) {
						logger.error(DELETE_BOOK_ERROR);
					}
                    
                    break;
                case 7:
                    
					try {
						goodReaders=statisticService.getBooksReadMoreThenOne();
					}
					catch (ServiceException e) {
						logger.error(GETTING_GOOD_READERS_ERROR);
					}
                    
					for(Map.Entry<String,Integer> entry: goodReaders.entrySet()){
						System.out.println(entry.getKey());
						System.out.println(entry.getValue());
					}

                    break;
                case 8:
                    
					try {
						badReaders=statisticService.getBooksReadLessThenTwo();
					}
					catch (ServiceException e) {
						logger.error(GETTING_BAD_READERS_ERROR);
					}
                    
					for(Map.Entry<Employee,Integer> entry: badReaders.entrySet()){
						System.out.println(entry.getKey().getName());
						System.out.println(entry.getKey().getBirthday());
						System.out.println(entry.getValue());
					}

                    break;
                    
                default:

                    flag = false;

                    break;

            }

        }
        
	}
	
	public static void connectionPoolInit(){
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        
        try {
			connectionPool.init();
		} 
        catch (ClassNotFoundException e1) {
        	logger.error(CONNECTION_POOL_INIT_ERROR);
		} 
        catch (FileNotFoundException e1) {
        	logger.error(CONNECTION_POOL_INIT_ERROR);
		} 
        catch (SQLException e1) {
        	logger.error(CONNECTION_POOL_INIT_ERROR);
		} 
        catch (IOException e1) {
        	logger.error(CONNECTION_POOL_INIT_ERROR);
		}
        
	}

}

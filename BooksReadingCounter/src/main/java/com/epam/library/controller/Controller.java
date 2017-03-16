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
    private static boolean init=false;
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
    private final static String AVAILABLE_OPTIONS="Available options:";
    private final static String SEE_ALL_BOOKS="1 - see all books";
    private final static String SEE_BOOKS_BY_ID="2 - see book by id";
    private final static String ADD_BOOK="3 - add book";
    private final static String UPDATE_BOOK="4 - update book";
    private final static String RENAME_BOOK="5 - rename book";
    private final static String DELETE_BOOK="6 - delete book";
    private final static String GET_GOOD_READERS="7 - get good readers";
    private final static String GET_BAD_READERS="8 - get bad readers";
    private final static String OTHER="Other - exit program";
    private final static String DESIRED_VALUE="Please input desired value";
    private final static String ID="id-";
    private final static String TITLE="title-";
    private final static String AUTHOR="author-";
    private final static String BRIEF="brief-";
    private final static String PUBLISH_YEAR="publish year-";
    private final static String INPUT_ID="Input id";
    private final static String INPUT_TITLE="Input title";
    private final static String INPUT_AUTHOR="Input author";
    private final static String INPUT_BRIEF="Input brief";
    private final static String INPUT_PUBLISH_YEAR="Input publish year";
    private final static String INPUT_OLD_TITLE="Input old title";
    private final static String INPUT_NEW_TITLE="Input new title";
    
    
	public static void main(String[] args) {
		String i = null;
		int number;
        boolean flag = true;
        
        if(!init){
        	connectionPoolInit();
        }
        
        while (flag) {
            System.out.println(AVAILABLE_OPTIONS);
            System.out.println(SEE_ALL_BOOKS);
            System.out.println(SEE_BOOKS_BY_ID);
            System.out.println(ADD_BOOK);
            System.out.println(UPDATE_BOOK);
            System.out.println(RENAME_BOOK);
            System.out.println(DELETE_BOOK);
            System.out.println(GET_GOOD_READERS);
            System.out.println(GET_BAD_READERS);
            System.out.println(OTHER);
            System.out.println(DESIRED_VALUE);
            
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
                    
                	seeAllBooks();              
                    break;

                case 2:
                	
                	seeBookById();                	
                    break;

                case 3:
                    
                	addBook();          
                    break;
                    
                case 4:
                    
                	updateBook();           
                    break;
                    
                case 5:

                	renameBook();                 
                    break;
                	
                case 6:
                    
                	deleteBook();                   
                    break;
                    
                case 7:
                    
                	getGoodReaders();
                    break;
                    
                case 8:
                    
                	getBadReaders();
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
        
        init=true;       
	}
	
	public static void seeAllBooks(){
		List<Book> books=null;
		ServiceFactory factory=ServiceFactory.getINSTANCE();
        BookService bookService=factory.getBookService();
		
		try {
			books = bookService.getAllBooks();
		} 
		catch (ServiceException e) {
			logger.error(GETTING_BOOKS_ERROR);
		}
        
        for(Book singleBook:books){
        	System.out.println(ID+singleBook.getId());
        	System.out.println(TITLE+singleBook.getTitle());
        	System.out.println(AUTHOR+singleBook.getAuthor());
        	System.out.println(BRIEF+singleBook.getBrief());
        	System.out.println(PUBLISH_YEAR+singleBook.getPublishYear());
        }
        
	}
	
	public static void seeBookById(){
		String strId=null;
		int id=-1;
		Book book=null;
		ServiceFactory factory=ServiceFactory.getINSTANCE();
        BookService bookService=factory.getBookService();
        
		System.out.println(INPUT_ID);
    	
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
        
        System.out.println(ID+book.getId());
    	System.out.println(TITLE+book.getTitle());
    	System.out.println(AUTHOR+book.getAuthor());
    	System.out.println(BRIEF+book.getBrief());
    	System.out.println(PUBLISH_YEAR+book.getPublishYear());
        
	}
	
	public static void addBook(){
		ServiceFactory factory=ServiceFactory.getINSTANCE();
        BookService bookService=factory.getBookService();
        String title=null;
        String author=null;
        String brief=null;
        String strPublishYear=null;
        int publishYear=-1;
        Book book=null;

		System.out.println(INPUT_TITLE);
    	
		try {
			title=reader.readLine();
		} 
		catch (IOException e) {
			logger.error(READING_ERROR);
		}
		
		System.out.println(INPUT_AUTHOR);
		
		try {
			author=reader.readLine();
		} 
		catch (IOException e) {
			logger.error(READING_ERROR);
		}
		
		System.out.println(INPUT_BRIEF);
		
		try {
			brief=reader.readLine();
		} 
		catch (IOException e) {
			logger.error(READING_ERROR);
		}
		
		System.out.println(INPUT_PUBLISH_YEAR);
		
		try {
			strPublishYear=reader.readLine();
		} 
		catch (IOException e) {
			logger.error(READING_ERROR);
		}
		
        publishYear=Integer.valueOf(strPublishYear);
        
        book=new Book();
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
        
	}
	
	public static void updateBook(){
		ServiceFactory factory=ServiceFactory.getINSTANCE();
        BookService bookService=factory.getBookService();
        String title=null;
        String author=null;
        String brief=null;
        String strPublishYear=null;
        int publishYear=-1;
        Book book=null;
        String strId=null;
		int id=-1;
        
		System.out.println(INPUT_ID);
    	
		try {
			strId=reader.readLine();
		} 
		catch (IOException e) {
			logger.error(READING_ERROR);
		}
		
        id=Integer.valueOf(strId);
        
        System.out.println(INPUT_TITLE);
        
		try {
			title=reader.readLine();
		} 
		catch (IOException e) {
			logger.error(READING_ERROR);
		}
		
		System.out.println(INPUT_AUTHOR);
		
		try {
			author=reader.readLine();
		}
		catch (IOException e) {
			logger.error(READING_ERROR);
		}
		
		System.out.println(INPUT_BRIEF);
		
		try {
			brief=reader.readLine();
		} 
		catch (IOException e) {
			logger.error(READING_ERROR);
		}
		
		System.out.println(INPUT_PUBLISH_YEAR);
		
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
        
	}
	
	public static void renameBook(){
		ServiceFactory factory=ServiceFactory.getINSTANCE();
        BookService bookService=factory.getBookService();
        String oldTitle=null;
        String newTitle=null;
        
		System.out.println(INPUT_OLD_TITLE);
    	
		try {
			oldTitle=reader.readLine();
		} 
		catch (IOException e) {
			logger.error(READING_ERROR);
		}
        
        System.out.println(INPUT_NEW_TITLE);
        
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
        
	}
	
	public static void deleteBook(){
		String strId=null;
		int id=-1;
		ServiceFactory factory=ServiceFactory.getINSTANCE();
        BookService bookService=factory.getBookService();
        
		System.out.println(INPUT_ID);
    	
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
        
	}
	
	public static void getGoodReaders(){
        ServiceFactory factory=ServiceFactory.getINSTANCE();
        StatisticService statisticService=factory.getStatisticService();
        Map<String,Integer> goodReaders=null;
        
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
        
	}
	
	public static void getBadReaders(){
		ServiceFactory factory=ServiceFactory.getINSTANCE();
        StatisticService statisticService=factory.getStatisticService();
        Map<Employee,Integer> badReaders=null;
        
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
        
	}

}

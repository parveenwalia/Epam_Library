package com.epam.library.dao.connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static final ConnectionPool instance = new ConnectionPool();
    private final static String HOME="user.home";
    private final static String PROPERTIES="/databaseConfig.properties";
    private final static String DRIVER="driver";
    private final static String URL="url";
    private final static String USER="user";
    private final static String PASSWORD="password";

    private BlockingQueue<Connection> availableConns;
    private BlockingQueue<Connection> usedConns;

    private ConnectionPool() {

    }

    public void init() throws SQLException,ClassNotFoundException,FileNotFoundException,IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream(System.getProperty(HOME) + PROPERTIES));

        String driver = prop.getProperty(DRIVER);
        String url = prop.getProperty(URL);
        String user = prop.getProperty(USER);
        String password = prop.getProperty(PASSWORD);

        Class.forName(driver);
        int poolSize = 5;  

        availableConns=new ArrayBlockingQueue<Connection>(poolSize);
        usedConns=new ArrayBlockingQueue<Connection>(poolSize);

        for(int i=0; i<poolSize; i++){
	        availableConns.add(DriverManager.getConnection(url,user,password));
	    }    

    }

    public Connection retrieve() throws SQLException,InterruptedException {
	    Connection con;
	    
        con=availableConns.take();
        usedConns.put(con);

	    return con;
    }

    public void putback(Connection c){

	    if (c != null) {
            usedConns.remove(c);
	        availableConns.add(c);
	    } 

    }

    public void close() throws NullPointerException,SQLException{
        Connection con;

	    for(int i=0;i<availableConns.size();i++){
            con=availableConns.remove();
            con.close();
        }

        for(int i=0;i<usedConns.size();i++){
            con=usedConns.remove();
            con.close();
        }

    }

    public static ConnectionPool getInstance() {
        return instance;
    }

}
package controller;


import java.sql.Connection;
 

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class models a database. Directly interacts with mysql database.
 */
public class Database {
    
    private static Database database = null;
    private Connection connection;
    private Statement statement;
    private ResultSet result;
    
    /**
     * Singleton class. Must use this method instead of constructor.
     * @return this
     */
    public static Database getInstance(){
        
        if(database == null){
            
            database = new Database("127.0.0.1:3306", "root", "1179215aA");
            return database;

        }
        
        return database;
        
    }
    
    /**
     * Private constructor
     */
    private Database(String host, String user, String pass){
        
        try { 
            
        	connection = DriverManager.getConnection("jdbc:mysql://"+host+"/quizprogram?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC", user, pass);
        	statement = connection.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    /**
     * Queries the database
     * @param query 
     * @return result of query
     */
    public ResultSet executeQuery(String query){
        
        result = null;
        
        try {
            result = statement.executeQuery(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
    
    /**
     * Executes command with no return in database
     * @param query
     */
    public void execute(String query){
        
        try {
            statement.execute(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public void setCredentials(String host, String user, String pass) {
    	database = new Database(host, user, pass);
    }
    
}

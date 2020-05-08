package controller;


import java.sql.Connection;
 

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 */
public class Database {
    
    private static Database database = null;
    private Connection connection;
    private Statement statement;
    private ResultSet result;
    
    public static Database getInstance(){
        
        if(database == null){
            
            database = new Database();
            return database;

        }
        
        return database;
        
    }
    
    private Database(){
        
        try { 
            
        	connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/quizprogram?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "1179215aA");
        	statement = connection.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public ResultSet executeQuery(String query){
        
        result = null;
        
        try {
            result = statement.executeQuery(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
    
    public void execute(String query){
        
        try {
            statement.execute(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
}

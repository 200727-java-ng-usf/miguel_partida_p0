package com.revature.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    /**
     * ConnectionFactory is a singelton design pattern
     * where there can only be one  "single" instance
     * bellow is an "eager" singelton
     * instatiated right away
     */
    private static ConnectionFactory connFactory = new ConnectionFactory();

    private Properties props = new Properties();

    /**
     * this will confirm the file inside of resources
     */
    private ConnectionFactory(){
        try {
            props.load(new FileReader("src/main/resources/application.properties"));
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static ConnectionFactory getInstance(){
        return connFactory;
    }
    /**
     * getConnection
     * class.forname gets the language specific driver; postgresql
     * the DriverManager then establishes the connection with the file inputs
 */
    public Connection getConnection(){

        Connection conn = null;

        try{
            //Force the JVM to load the PostGreSQL
           Class.forName("org.postgresql.Driver");
//specifing that  we are using the jdbc and postgresql
            conn = DriverManager.getConnection(
                    props.getProperty("url"),
                    props.getProperty("username"),
                    props.getProperty("password")
            );

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        if(conn==null){
            throw new RuntimeException("Failed to establish connedction");
        }
        return conn;
    }

    /**
     * Here is part of the singelton design pattern
     * ensuring that our connectionfactory is not duplicated
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone()throws CloneNotSupportedException{
        throw new CloneNotSupportedException();
    }

}

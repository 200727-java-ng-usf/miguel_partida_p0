package com.revature.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static ConnectionFactory connFactory = new ConnectionFactory();

    private Properties props = new Properties();

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

    public Connection getConnection(){

        Connection conn = null;

        try{
            //Force the JVM to load the PostGreSQL
            Class.forName("org.postgresql.Driver");
//specifing that                               we are using the jdbc and postgresql
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


    @Override
    protected Object clone()throws CloneNotSupportedException{
        throw new CloneNotSupportedException();
    }

}
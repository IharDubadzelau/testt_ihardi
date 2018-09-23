package ru.javarush.tt_ihardu.testtask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import ru.javarush.tt_ihardu.testtask.config.PropertiesDB;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

@Service
public class ConnectDB {
    private static final String DRIVER_MYSQL = "com.mysql.jdbc.Driver";
    private static final String FILE_SCRIPT = "scripts/startscript.sql";
    private Connection connection = null;
    private static ConnectDB connectDB;

    @Autowired
    private PropertiesDB propertiesDB;

    private ConnectDB() {
    }

    public static ConnectDB getConnection() {
        if (connectDB == null)
            connectDB = new ConnectDB();

        return connectDB;
    }

    private boolean isConnect(){
        if ( propertiesDB==null ){
            System.out.println("Can't loaded DB connection properties.");
            return false;
        }
       //---
        try {
            Class.forName(DRIVER_MYSQL);
        } catch (ClassNotFoundException e) {
            System.out.println("Can't get class MySQL. No driver found");
            e.printStackTrace();
            return false;
        }
       //---
        try {
            connection = DriverManager.getConnection(propertiesDB.getUrl(), propertiesDB.getUsername(), propertiesDB.getPassword());
        } catch (SQLException e) {
            System.out.println("Can't get connection to DB MySQL. Incorrect login & password or properties URL: "+propertiesDB.getUrl());
            e.printStackTrace();
            return false;
        }
       //---
        return true;
    }

    private void disConnect() {
        if ( connection!=null ) {
            try {
                if ( !connection.isClosed() )
                    connection.close();
            } catch (SQLException e) {
                System.out.println("An error occurred while trying to close the database connection.");
                e.printStackTrace();
            }

        }
    }

    private String loadResource() throws IOException {
        ClassPathResource resource = new ClassPathResource(FILE_SCRIPT);
        InputStream inputStream = resource.getInputStream();
        Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
        String result = scanner.hasNext() ? scanner.next() : "";
        scanner.close();
        inputStream.close();
        return result;
    }

    public boolean goScript(){
        if ( isConnect() ) {
            try {
                String resource = loadResource();
                final String[] scripts = resource.split(";");

                for (String script:scripts)
                    if ( script.trim().length()>0 )
                        connection.createStatement().execute(script.trim());

                return true;
            } catch (IOException e) {
                System.out.println("Error loading script SQL file.");
                e.printStackTrace();
                return false;
            } catch (SQLException e) {
                System.out.println("Script execution error.");
                e.printStackTrace();
            } finally {
                disConnect();
            }
        }

        return false;
    }
}
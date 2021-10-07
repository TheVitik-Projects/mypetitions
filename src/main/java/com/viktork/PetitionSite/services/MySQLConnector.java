package com.viktork.PetitionSite.services;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnector {
    private static final String jdbc_driver = "com.mysql.cj.jdbc.Driver";
    private String db_url;

    private String host;
    private String user;
    private String password;
    private String db;

    private Connection con;

    public void mysql_data(String host,String user,String password){
        this.host=host;
        this.user=user;
        this.password=password;
    }
    public void mysql_select_db(String db){
        this.db=db;
    }
    public void mysql_connect() {
        this.db_url="jdbc:mysql://"+host+"/"+db+"?serverTimezone=Europe/Kiev&useSSL=false";
        try {
            Class.forName(jdbc_driver).getDeclaredConstructor().newInstance();
            Connection con = DriverManager.getConnection(db_url, user, password);

            this.con=con;

        }
        catch(Exception ex){
            System.out.println("MYSQL CONNECTION ERROR!");
        }

    }
    public Connection getConnection(){
        return con;
    }
}

package com.viktork.PetitionSite.models;

import com.viktork.PetitionSite.services.MySQLConnector;
import com.viktork.PetitionSite.services.MySQLData;
import com.viktork.PetitionSite.services.MySQLQuery;

import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetitionProcessor {
    private static final MySQLConnector connector = new MySQLConnector();

    public PetitionProcessor(){
        try {
            connector.mysql_data(MySQLData.host, MySQLData.user, MySQLData.password);
            connector.mysql_select_db(MySQLData.db);
            connector.mysql_connect();
        }
        catch (Exception ex){
            System.out.println("ERROR");
        }
    }

    public void addPetition(String title, String userId) throws SQLException {
        MySQLQuery query = new MySQLQuery(connector.getConnection());
        String keys = "creator,title,date,votes,active";
        int id = Integer.parseInt(userId);
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date date = new java.sql.Date(utilDate.getTime());
        ArrayList values = new ArrayList(){{
            add(id);
            add(title);
            add(date);
            add(0);
            add(1);
        }};
        query.mysql_insert("petitions",keys,values);
    }
    public void deletePetition(String petId,String userId) throws SQLException{
        MySQLQuery query = new MySQLQuery(connector.getConnection());
        if(getCreator(petId).equals(userId)) {
            query.mysql_update("petitions", "active", "0", "id=" + petId);
        }
    }

    private String getCreator(String id) throws SQLException {
        MySQLQuery query = new MySQLQuery(connector.getConnection());
        ResultSet rs = query.mysql_select("*", "petitions", "id=" + id);
        rs.next();
        return rs.getString("creator");
    }
}

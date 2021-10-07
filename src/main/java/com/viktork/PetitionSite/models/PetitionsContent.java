package com.viktork.PetitionSite.models;

import com.viktork.PetitionSite.services.MySQLConnector;
import com.viktork.PetitionSite.services.MySQLData;
import com.viktork.PetitionSite.services.MySQLQuery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class PetitionsContent {
    private ResultSet rs;
    private Connection con;
    public PetitionsContent() {
        MySQLConnector connector = new MySQLConnector();
        connector.mysql_data(MySQLData.host, MySQLData.user, MySQLData.password);
        connector.mysql_select_db(MySQLData.db);
        connector.mysql_connect();
        con=connector.getConnection();
        MySQLQuery query = new MySQLQuery(con);
        ResultSet rs = query.mysql_select("*", "petitions");
        this.rs=rs;

    }
    public Petition create() throws SQLException {
        Petition petition = new Petition();
        petition.setId(rs.getInt("id"));
        petition.setCreator(rs.getInt("creator"));
        petition.setVotes(rs.getInt("votes"));
        petition.setActive(rs.getInt("active"));
        petition.setTitle(rs.getString("title"));
        petition.setDate(rs.getString("date"));
        return petition;
    }
    public ArrayList getActive() throws SQLException {
        ArrayList<Petition> petitions = new ArrayList<>();
        while(rs.next()) {
            Petition pet = create();
            if (rs.getInt("active") == 1) {
                petitions.add(pet);
            }
        }
        Collections.reverse(petitions);
        return petitions;
    }
    public ArrayList getClosed() throws SQLException{
        ArrayList<Petition> petitions = new ArrayList<>();
        while(rs.next()) {
            Petition pet = create();
            if (rs.getInt("active") == 0) {
                petitions.add(pet);
            }
        }
        Collections.reverse(petitions);
        return petitions;
    }
    public ArrayList getMyPetitions(String id) throws SQLException{
        ArrayList<Petition> petitions = new ArrayList<>();
        while(rs.next()) {
            Petition pet = create();
            if (rs.getString("creator").equals(id) && rs.getString("active").equals("1")) {
                petitions.add(pet);
            }
        }
        Collections.reverse(petitions);
        return petitions;
    }
    public ArrayList getSearch(int id) throws SQLException{
        ArrayList<Petition> petitions = new ArrayList<>();
        while(rs.next()) {
            Petition pet = create();
            if (rs.getInt("id")==id) {
                petitions.add(pet);
            }
        }
        return petitions;
    }
}

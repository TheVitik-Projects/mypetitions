package com.viktork.PetitionSite.models;

public class Petition {
    private int id;
    private int creator;
    private String title;
    private String date;
    private int votes;
    private int active;

    public Petition() {

    }
    public Petition(int id,int creator,String title,String date,int votes,int active){
        this.id=id;
        this.creator=creator;
        this.title=title;
        this.date=date;
        this.votes=votes;
        this.active=active;
    }



    public void setId(int id){
        this.id=id;
    }
    public void setCreator(int creator){
        this.creator=creator;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public int getCreator() {
        return creator;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public int getVotes() {
        return votes;
    }

    public int getActive() {
        return active;
    }
}

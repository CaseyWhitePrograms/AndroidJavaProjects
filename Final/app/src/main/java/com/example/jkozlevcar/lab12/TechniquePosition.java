// Project:     Java III project
// Author:      White
// Date:        3/5/19
// File:        TechniquePosition.java
// Description: This class in the abstract base class for TechniquePosition

package com.example.jkozlevcar.lab12;

public class TechniquePosition
{
    // fields
    String position;
    String attack;
    String rank;
    String hours;
    String date;

    // constructor. Parameters for all fields
    public TechniquePosition(String position, String attack, String rank,
                             String hours, String date)
    {
        // call the superclass constructor
        this.position = position;
        this.attack = attack;
        this.rank = rank;
        this.hours = hours;
        this.date = date;
    }


    public String toString()
    {
        return position + ", " + attack + ", " + rank + ", " + hours + ", " + date  ;
    }


    //public get/set methods
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAttack() {
        return attack;
    }

    public void setAttack(String attack) {
        this.attack = attack;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}

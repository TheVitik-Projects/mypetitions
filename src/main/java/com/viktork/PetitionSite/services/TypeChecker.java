package com.viktork.PetitionSite.services;

import java.sql.Date;

public class TypeChecker {

    public boolean isInteger(Object object) {
        if(object instanceof Integer) {
            return true;
        } else {
            String string = object.toString();

            try {
                Integer.parseInt(string);
            } catch(Exception e) {
                return false;
            }
        }

        return true;
    }

    public boolean isDate(Object object) {
        if(object instanceof Date) {
            return true;
        } else {
            try {
                java.sql.Date date = new java.sql.Date((Long) object);
            } catch(Exception e) {
                return false;
            }
        }
        return true;
    }
}

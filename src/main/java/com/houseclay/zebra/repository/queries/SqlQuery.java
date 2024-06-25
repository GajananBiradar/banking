package com.houseclay.zebra.repository.queries;

/**
 * File containing all the Native Queries for the Application
 */
public class SqlQuery {

    public static final String LISTOFPROPERTYIMAGES="SELECT * FROM IMAGES WHERE image_map=?1";

    public static final String OWNERLEADFINDBYCONTACTNUMBER = "SELECT * FROM LEADS_OWNER WHERE contact_number=?1";

}

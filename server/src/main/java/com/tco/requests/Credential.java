package com.tco.requests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Credential {
    public final static transient Logger log = LoggerFactory.getLogger(Credential.class);

    // shared user with read-only access
    private static String useTunnel = System.getenv("CS314_USE_DATABASE_TUNNEL");
    


    final static String USER = "cs314-db";
    final static String PASSWORD = "eiK5liet1uej";
    private static String dburl = "";

    public static void whichEnv(){ //must put if else in a method body
        log.info("SAMUEL: useTunnel: {}", useTunnel);
        // When using Locally (by Port Forwarding)
        if (useTunnel != null && useTunnel.equals("true")) {
            dburl = "jdbc:mariadb://127.0.0.1:56247/cs314";
        }
        // Else, we must be running against the production database directly
        else {
            dburl = "jdbc:mariadb://faure.cs.colostate.edu/cs314";
        }
    }
    
    static String url() {
        return dburl;
    }
}

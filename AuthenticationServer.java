/**
 * Copyright 2020
 *
 * Authentication Server (Abstraction)
 *
 * @author Christian Lorza
 *
 * Created at     : 2020-02-10 21:55:28 
 * Last modified  : 2020-02-10 22:06:43
 */

// * Important Note: The data flow and implementation of methods does not accurately
// * reflect the final structure of the application. The existing methods
// * are simply immitating the behavior of certain classes and processes which will
// * contain much stricter validation, sanitisation and security practices once completed

// * The purpose of these methods is to establish  base line functionality to build a comprehensive
// * collection of tests. TDD

import java.util.ArrayList;
import java.sql.Timestamp;

public abstract class AuthenticationServer {
    private static String               valid_username  = "jimmy";
    private static String               valid_password  = "securepassword123";
    private static String               access_level    = "default";
    private static ArrayList<String>    authLog         = new ArrayList<String>();

    public static boolean authenticate (String username, String pass, String accessRights) {
        // * Add authentication attempt 
        Timestamp ts = new Timestamp(System.currentTimeMillis());

        if (username == valid_username && pass == valid_password && access_level == accessRights) {
            authLog.add("Authentication attempt at: " + ts + " - credentials: " + username + " " + pass + " (success)");
            return true;
        } else {
            authLog.add("Authentication attempt at: " + ts + " - credentials: " + username + " " + pass + " (failed)");
            return false;
        }
    }

    public static boolean logout () {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        authLog.add(valid_username + "Logout at: " + ts);
        return true;
    }

    public static ArrayList<String> getAuthLog () {
        return authLog;
    }
}
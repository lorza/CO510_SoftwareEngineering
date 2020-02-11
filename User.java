/**
 * Copyright 2020
 *
 * User class with basic interaction methods
 *
 * @author Christian Lorza
 *
 * Created at     : 2020-02-07 20:54:22 
 * Last modified  : 2020-02-10 21:12:13
 */

// * Important Note: The data flow and implementation of methods does not accurately
// * reflect the final structure of the application. The existing methods
// * are simply immitating the behavior of certain classes and processes which will
// * contain much stricter validation, sanitisation and security practices once completed

// * The purpose of these methods is to establish  base line functionality to build a comprehensive
// * collection of tests. TDD

public class User {
    private boolean authenticated;
    private String  accessRights;

    public User () {
        authenticated = false;
        accessRights = null;
        System.out.println("User constructed");
    }

    public boolean login (String username, String password, String accessRights) {

        // * Reaches out to authentication server and validates
        // * Assume private credentials are stored on server and not in class
        boolean authed = AuthenticationServer.authenticate(username, password, accessRights);
        setAuthStatus(authed);

        // * If auth is successful, set access rights that were requested
        // * Assume access rights are set server side
        if (authed == true) {setAccessRights(accessRights);}

        //* Print message to terminal
        String message = authed == true ? "successfully authenticated" : "invalid credentials";
        System.out.println(message);

        return authenticated;
    }

    public boolean logout () {
        // * If user is not logged in, prevent
        if (getAuthStatus() == false) {return false;}
        boolean res = AuthenticationServer.logout();
       if (res == true) {
           setAuthStatus(false);
           setAccessRights("");
           return true;
       }
       
       return false;
    }
    
    public boolean  getAuthStatus   ()                 {return authenticated;}
    public String   getAccessRights ()                 {return accessRights;}
    public void     setAuthStatus   (boolean status)   {authenticated   = status;}
    public void     setAccessRights (String s)         {accessRights    = s;}
}
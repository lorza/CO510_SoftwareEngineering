

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class UserTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class UserTest
{
    private User user;

    // * Create new user
    public UserTest () {user = new User();}
    
    @Test
    // * Invalid login credentials - expect false return value
    public void invalidLogin () {
        // * random invalid credentials - expect false
        boolean result = user.login("ashdkasdjhas", "126739187263");
        assertEquals(false, result);
        assertEquals(false, user.getAuthStatus());
        
        // * pass null values for both - expect false
        boolean nullInput = user.login(null, null);
        assertEquals(false, nullInput);
        assertEquals(false, user.getAuthStatus());
        
        // * pass empty strings for both - expect false
        boolean emptyInput = user.login("", "");
        assertEquals(false, emptyInput);
        assertEquals(false, user.getAuthStatus());
    }
    
    @Test
    // * Login with valid credentials & Check authentication status
    // * Expect successful login and true authStatus
    public void validLogin () {
        boolean result = user.login("jimmy", "securepassword123");
        assertEquals(true, result);
        assertEquals(true, user.getAuthStatus());
    }

    // * Login with valid credentials but pass in invalid access level
    public void validLoginInvalidAccess () {
        boolean result = user.login("jimmy", "securepassword123");
        assertEquals(false, result);
        assertEquals(false, user.getAuthStatus());
    }

    @Test
    // * Logout. Valid login then check logout. Expect true, false authenticated and empty string for accessRights
    public void logout () {
        boolean loginRes = user.login("jimmy", "securepassword123");

        // * Was login valid?
        assertEquals(true, loginRes);

        // * Test Logout
        boolean logoutRes = user.logout();
        assertEquals(true, logoutRes);

        // * Is authentication on user object false?
        assertEquals(false, user.getAuthStatus());

        // * Is access level empty
        assertEquals("", user.getAccessRights());
    }

    @Test
    // * Invalid Logout - User is not logged in but attempts to logout
    public void invalidLogout () {
        assertEquals(false, user.logout());
    }
}

package at.technikum.apps.mtcg.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//service that provides logic for login of a user
public class SessionService {
    private static Map<String,String> validTokens = new HashMap<>();
    public String generateToken(String username){
        String token = username + "-mtcgToken";
        validTokens.put(token, username);
        return token;
    }
    public boolean isLoggedInAsAdmin(String token){
        if (isLoggedIn(token)) {
            return validTokens.get(token).equals("admin");
        }
        return false;
    }
    public boolean isLoggedIn(String token){
        return validTokens.containsKey(token);
    }
}

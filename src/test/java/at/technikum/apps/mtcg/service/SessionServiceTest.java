package at.technikum.apps.mtcg.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SessionServiceTest {


    private SessionService sessionService;

    @BeforeEach
    void setUp() {
        sessionService = new SessionService();
    }

    @Test
    void generateToken_validUsername_tokenGenerated() {
        String username = "testUser";
        String token = sessionService.generateToken(username);

        assertNotNull(token);
        assertTrue(token.startsWith(username));
        assertTrue(token.endsWith("-mtcgToken"));
    }

    @Test
    void testIsLoggedIn() {
        String username = "testUser";
        String token = sessionService.generateToken(username);

        boolean result = sessionService.isLoggedIn(token);

        assertTrue(result);
    }

    @Test
    void testIsLoggedInAsAdmin() {
        String username = "admin";
        String token = sessionService.generateToken(username);

        boolean result = sessionService.isLoggedInAsAdmin(token);

        assertTrue(result);
    }
}
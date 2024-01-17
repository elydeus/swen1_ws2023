package at.technikum.apps.mtcg.repository;

import at.technikum.apps.mtcg.data.Database;
import at.technikum.apps.mtcg.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DatabaseUserRepositoryTest {
/*
    @Mock
    private Database mockDatabase;

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    private DatabaseUserRepository databaseUserRepository;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        when(mockDatabase.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        databaseUserRepository = new DatabaseUserRepository(mockDatabase);
    }

    @Test
    public void testLogin() throws SQLException {
        String username = "testUser";
        String password = "testPassword";
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getString("user_id")).thenReturn("1");
        when(mockResultSet.getString("username")).thenReturn(username);
        when(mockResultSet.getString("password")).thenReturn(password);
        when(mockResultSet.getInt("elo")).thenReturn(100);
        when(mockResultSet.getInt("coins")).thenReturn(20);
        when(mockResultSet.getString("deck_id")).thenReturn("1");
        when(mockResultSet.getString("bio")).thenReturn("bio");
        when(mockResultSet.getString("image")).thenReturn("image");
        when(mockResultSet.getString("name")).thenReturn("name");

        Optional<User> result = databaseUserRepository.login(username, password);

        assertTrue(result.isPresent());
        assertEquals(username, result.get().getUsername());
        assertEquals(password, result.get().getPassword());
    }*/
}
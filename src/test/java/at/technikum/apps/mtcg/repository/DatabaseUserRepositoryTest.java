package at.technikum.apps.mtcg.repository;

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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class DatabaseUserRepositoryTest {
/*
    @Mock
    private Connection mockConnection;
    @Mock
    private PreparedStatement mockStatement;
    @Mock
    private ResultSet mockResultSet;

    private DatabaseUserRepository repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        repository = new DatabaseUserRepository(mockConnection);
    }
    public void testFind_ReturnsUser() throws Exception {
        // Arrange
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true); // Mocks return of one result
        // Mock weitere ResultSet Methodenaufrufe, um eine User-Instanz zu erzeugen

        // Act
        Optional<User> result = repository.find("username");

        // Assert
        assertTrue(result.isPresent());
        // Weitere Assertions, um die Daten des Benutzers zu überprüfen
    }
    @Test
    public void testSave_SavesUserSuccessfully() throws Exception {
        // Arrange
        User user = new User(); // Erstellen Sie eine User-Instanz mit entsprechenden Daten
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1);

        // Act
        repository.save(user);

        // Assert
        verify(mockStatement, times(1)).executeUpdate();
        // Weitere Assertions, um zu überprüfen, ob die Daten korrekt in das PreparedStatement eingefügt wurden
    }
    @Test
    public void testFind_ThrowsSQLException() throws Exception {
        // Arrange
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenThrow(new SQLException());

        // Act & Assert
        assertThrows(DataAccessException.class, () -> repository.find("username"));
    }*/
}
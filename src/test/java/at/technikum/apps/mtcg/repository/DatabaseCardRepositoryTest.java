package at.technikum.apps.mtcg.repository;

import at.technikum.apps.mtcg.repository.DatabaseCardRepository;
import at.technikum.apps.mtcg.entity.Card;
import at.technikum.apps.mtcg.data.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class DatabaseCardRepositoryTest {
/*
    @Mock
    private Database database;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    private DatabaseCardRepository repository;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        when(database.getConnection()).thenReturn(connection);
        repository = new DatabaseCardRepository(database);
    }

    @Test
    void testFindAllWithNonEmptyDatabase() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false); // Simulate one result
        when(resultSet.getString("id")).thenReturn("card1");
        when(resultSet.getString("name")).thenReturn("Test Card");
        when(resultSet.getInt("damage")).thenReturn(10);
        when(resultSet.getString("package_id")).thenReturn("pack1");
        when(resultSet.getString("type")).thenReturn("monster");

        List<Card> cards = repository.findAll();

        assertFalse(cards.isEmpty(), "Expected non-empty list of cards");
        assertEquals(1, cards.size(), "Expected one card in the result");
        Card card = cards.get(0);
        assertEquals("card1", card.getId());
        assertEquals("Test Card", card.getName());
        assertEquals(10, card.getDamage());
        assertEquals("pack1", card.getPackageId());
        assertEquals("monster", card.getType());
    }

    @Test
    void testFindAllWithEmptyDatabase() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false); // Simulate no results

        List<Card> cards = repository.findAll();

        assertTrue(cards.isEmpty(), "Expected empty list of cards");
    }

    @Test
    void testFindWithValidId() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false); // Simulate one result
        when(resultSet.getString("id")).thenReturn("card1");
        when(resultSet.getString("name")).thenReturn("Test Card");
        when(resultSet.getInt("damage")).thenReturn(10);
        when(resultSet.getString("package_id")).thenReturn("pack1");
        when(resultSet.getString("type")).thenReturn("monster");

        Card card = repository.find("card1");

        assertNotNull(card, "Expected a non-null card");
        assertEquals("card1", card.getId());
        assertEquals("Test Card", card.getName());
        assertEquals(10, card.getDamage());
        assertEquals("pack1", card.getPackageId());
        assertEquals("monster", card.getType());
    }

    @Test
    void testFindWithInvalidId() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false); // Simulate no results

        Card card = repository.find("invalidId");

        assertNull(card, "Expected a null result for an invalid ID");
    }*/
}

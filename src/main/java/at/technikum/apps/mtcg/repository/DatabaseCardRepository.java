package at.technikum.apps.mtcg.repository;

import at.technikum.apps.mtcg.data.Database;
import at.technikum.apps.mtcg.entity.Card;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DatabaseCardRepository implements CardRepository {

    private final String FIND_ALL_SQL = "SELECT * FROM card";
    private final String SAVE_SQL = "INSERT INTO card(id, name, description, done) VALUES(?, ?, ?, ?)";

    private final Database database = new Database();

    @Override
    public List<Card> findAll() {
        List<Card> cards = new ArrayList<>();

        try (
                Connection con = database.getConnection();
                PreparedStatement pstmt = con.prepareStatement(FIND_ALL_SQL);
                ResultSet rs = pstmt.executeQuery()
        ) {
            while (rs.next()) {
                Card card = new Card(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getBoolean("done")
                );
                cards.add(card);
            }

            return cards;
        } catch (SQLException e) {
            return cards;
        }
    }

    @Override
    public Optional<Card> find(int id) {
        return Optional.empty();
    }

    @Override
    public Card save(Card card) {
        try (
                Connection con = database.getConnection();
                PreparedStatement pstmt = con.prepareStatement(SAVE_SQL)
        ) {
            pstmt.setString(1, card.getId());
            pstmt.setString(2, card.getName());
            pstmt.setString(3, card.getDescription());
            pstmt.setBoolean(4, card.isDone());

            pstmt.execute();
        } catch (SQLException e) {
            // THOUGHT: how do i handle exceptions (hint: look at the TaskApp)
        }

        return card;
    }

}

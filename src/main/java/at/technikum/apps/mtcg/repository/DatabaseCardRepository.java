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

    private final String FIND_ALL_SQL = "SELECT * FROM cards";

    private final String FIND_CARD = "SELECT * FROM cards where id = ?";
    private final String SAVE = "INSERT INTO cards(id, name, damage, package_id, elementType, type) VALUES(?, ?, ?, ?, ?, ?)";

    private final String FIND_ALL_CARDS_BY_USER = "SELECT c.id, c.name, c.damage, c.package_id, c.elementType, c.type FROM stacks s JOIN cards c ON s.card_id = c.id WHERE s.user_id = ?";

    private final Database database = Database.getInstance();


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
                        rs.getInt("damage"),
                        rs.getString("package_id"),
                        rs.getString("elementType"),
                        rs.getString("type")
                );
                cards.add(card);
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception! Message: " + e.getMessage());
        }
        return cards;
    }

    @Override
    public Card find(String id) {
        Card card = new Card();
        try (
                Connection con = database.getConnection();
                PreparedStatement pstmt = con.prepareStatement(FIND_CARD);
        ) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                card.setId(rs.getString("id"));
                card.setName(rs.getString("name"));
                card.setDamage(rs.getInt("damage"));
                card.setPackageId(rs.getString("package_id"));
                card.setElementType(rs.getString("elementType"));
                card.setType(rs.getString("type"));
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception! Message: " + e.getMessage());
        }
        return card;
    }

    @Override
    public Card save(Card card) {
        try (
                Connection con = database.getConnection();
                PreparedStatement pstmt = con.prepareStatement(SAVE)
        ) {
            pstmt.setString(1, card.getId());
            pstmt.setString(2, card.getName());
            pstmt.setInt(3, card.getDamage());
            pstmt.setString(4, card.getPackageId());
            if(card.getName().contains("Fire")){
                pstmt.setString(5, "Fire");
            } else if (card.getName().contains("Water")) {
                pstmt.setString(5, "Water");
            }else{
                pstmt.setString(5, "Normal");
            }
            if(card.getName().contains("Spell")){
                pstmt.setString(6, "Spell");
            }else{
                pstmt.setString(6, "Monster");
            }
            pstmt.execute();
        } catch (SQLException e) {
            System.err.println("SQL Exception! Message: " + e.getMessage());
        }

        return card;
    }

    @Override
    public List<Card> findAllCardsByUser(String user_id) {
        List<Card> cardsFromUser = new ArrayList<>();
        try (
                Connection con = database.getConnection();
                PreparedStatement pstmt = con.prepareStatement(FIND_ALL_CARDS_BY_USER)
        ) {
            pstmt.setString(1, user_id);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Card card = new Card(
                            rs.getString("id"),
                            rs.getString("name"),
                            rs.getInt("damage"),
                            rs.getString("package_id"),
                            rs.getString("type")
                    );
                    cardsFromUser.add(card);
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception! Message: " + e.getMessage());
        }
        return cardsFromUser;
    }
    }

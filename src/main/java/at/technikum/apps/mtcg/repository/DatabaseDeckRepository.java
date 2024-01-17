package at.technikum.apps.mtcg.repository;

import at.technikum.apps.mtcg.data.Database;
import at.technikum.apps.mtcg.entity.Card;
import at.technikum.apps.mtcg.entity.Deck;
import at.technikum.apps.mtcg.entity.Package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseDeckRepository implements DeckRepository {

    private final String FIND_ALL = "SELECT card_id FROM deckcards where deck_id = ?";
    private final String SAVE = "INSERT INTO decks(deck_id, user_id) VALUES(?, ?)";

    private final String GET_DECK_ID = "SELECT deck_id FROM decks WHERE user_id = ?";

    private final String GET_USER_ID = "SELECT id FROM users WHERE username = ?";

    private final String DO_CARDS_MATCH_USER = "SELECT COUNT(*) FROM stacks WHERE user_id = ? and card_id IN (?, ?, ?, ?)";

    private final String IS_CARD_AVAILABLE_FOR_TRADE = "SELECT dealerCardId, customerCardId FROM trades WHERE dealerCardId = ? OR customerCardId = ?";
    private final String SAVE_CARDS_IN_DECK = "INSERT INTO deckcards(card_id, deck_id) VALUES (?, ?)";
    private final Database database = Database.getInstance();


    @Override
    public void save(Deck deck, String user_id) {
        try (
                Connection con = database.getConnection();
                PreparedStatement pstmt = con.prepareStatement(SAVE)
        ) {

            pstmt.setString(1, deck.getDeck_id());
            pstmt.setString(2, user_id);
            pstmt.execute();

        } catch (SQLException e) {
            System.err.println("SQL Exception! Message: " + e.getMessage());
        }
    }

    @Override
    public String getDeck_Id(String user_id) {
        String deck_id = null;

        try (
                Connection con = database.getConnection();
                PreparedStatement pstmt = con.prepareStatement(GET_DECK_ID)
        ) {
            pstmt.setString(1, user_id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    deck_id = rs.getString("deck_id");
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception! Message: " + e.getMessage());
        }
        return deck_id;

    }

    @Override
    public String getUserId(String username) {
        String id = null;

        try (
                Connection con = database.getConnection();
                PreparedStatement pstmt = con.prepareStatement(GET_USER_ID)
        ) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    id = rs.getString("id");
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception! Message: " + e.getMessage());
        }
        return id;

    }

    @Override
    public List<String> findAll(String deck_id) {
        List<String> cardsInDeck = new ArrayList<>();
        try (
                Connection con = database.getConnection();
                PreparedStatement pstmt = con.prepareStatement(FIND_ALL)
        ) {
            pstmt.setString(1, deck_id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String card = rs.getString("card_id");
                if(card != null){
                    cardsInDeck.add(card);
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception! Message: " + e.getMessage());
        }
        return cardsInDeck;

    }

    @Override
    public boolean checkIfCardsMatchUser(List<String> cards, String user_id) {
        try (
                Connection con = database.getConnection();
                PreparedStatement pstmt = con.prepareStatement(DO_CARDS_MATCH_USER)
        ) {
            pstmt.setString(1, user_id);
            for (int i = 0; i < cards.size(); i++) {
                pstmt.setString(i + 2, cards.get(i));
            }
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() && (rs.getInt(1) == cards.size());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public void saveCardsInDeck(List<String> cards, String deck_id) {
        try (
                Connection con = database.getConnection();
                PreparedStatement pstmt = con.prepareStatement(SAVE_CARDS_IN_DECK)
        ) {
            for (int i = 0; i < cards.size(); i++) {
                pstmt.setString(1, cards.get(i));
                pstmt.setString(2, deck_id);
                pstmt.execute();
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception! Message: " + e.getMessage());
        }
    }

    @Override
    public void updateCardsInDeck(List<String> cards, String deck_id) {
        try (
                Connection con = database.getConnection();
                PreparedStatement pstmt = con.prepareStatement(SAVE_CARDS_IN_DECK)
        ) {
            for (int i = 0; i < cards.size(); i++) {
                pstmt.setString(1, cards.get(i));
                pstmt.setString(2, deck_id);
                pstmt.execute();
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception! Message: " + e.getMessage());
        }
    }

    @Override
    public boolean isCardAvailableForTrade(String cardId) {
        String dealerCardId = null;
        String customerCardId = null;

        try (
                Connection con = database.getConnection();
                PreparedStatement pstmt = con.prepareStatement(IS_CARD_AVAILABLE_FOR_TRADE)
        ) {
            pstmt.setString(1, cardId);
            pstmt.setString(2, cardId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    dealerCardId = rs.getString("dealerCardId");
                    customerCardId = rs.getString("customerCardId");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return dealerCardId != null || customerCardId != null;
    }
}

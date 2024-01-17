package at.technikum.apps.mtcg.repository;

import at.technikum.apps.mtcg.data.Database;
import at.technikum.apps.mtcg.entity.Card;
import at.technikum.apps.mtcg.entity.Package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DatabasePackageRepository implements PackageRepository {
    private final String FIND_ALL_SQL = "SELECT * FROM packages";
    private final String SAVE_SQL = "INSERT INTO packages(id, user_id) VALUES(?, ?)";
    private final String GET_COINS_FROM_USER = "SELECT coins FROM users WHERE username = ?";

    private final String GET_ID_FROM_USER = "SELECT id FROM users WHERE username = ?";
    private final String GET_ID_FROM_PACKAGE = "SELECT * FROM packages LIMIT 1";

    private final String GET_CARDS = "SELECT id FROM cards WHERE package_id = ?";

    private final String UPDATE_USER_COINS = "UPDATE users SET coins = coins - ? WHERE username = ?";

    private final String DELETE = "DELETE FROM packages WHERE id = ?";
    private final Database database = Database.getInstance();
    private final CardRepository cardRepository = new DatabaseCardRepository();


    @Override
    public Package save(Package pkg) {
        try (
                Connection con = database.getConnection();
                PreparedStatement pstmt = con.prepareStatement(SAVE_SQL)
        ) {

            pstmt.setString(1, pkg.getId());
            pstmt.setString(2, pkg.getUserId());
            pstmt.execute();

            for (Card card:pkg.getCards()) {
                card.setPackageId(pkg.getId());
                cardRepository.save(card);
            }

        } catch (SQLException e) {
            // THOUGHT: how do i handle exceptions (hint: look at the TaskApp)
        }
        return pkg;
    }


    @Override
    public Package update(Package oldPkg, Package newPkg) {
        return null;
    }

    @Override
    public Optional<Package> find(String packageId) {
        return Optional.empty();
    }

    @Override
    public Set<Package> findAll() {
        return null;
    }
    @Override
    public int getCoinsFromUser(String username){
        int user_coins = 0;

        try (
                Connection con = database.getConnection();
                PreparedStatement pstmt = con.prepareStatement(GET_COINS_FROM_USER)
        ) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    user_coins = rs.getInt("coins");
                }
            }
        }catch (SQLException e){
            System.err.println("SQL Exception! Message: " + e.getMessage());
        }
        return user_coins;

    }

    @Override
    public String getIdFromUser(String username){
        String id = null;

        try (
                Connection con = database.getConnection();
                PreparedStatement pstmt = con.prepareStatement(GET_ID_FROM_USER)
        ) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    id = rs.getString("id");
                }
            }
        }catch (SQLException e){
            System.err.println("SQL Exception! Message: " + e.getMessage());
        }
        return id;

    }

    @Override
    public String getIdFromPackage(){
        String id = null;

        try (
                Connection con = database.getConnection();
                PreparedStatement pstmt = con.prepareStatement(GET_ID_FROM_PACKAGE)
        ) {
            try (ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    id = rs.getString("id");
                }
            }
        }catch (SQLException e){
            System.err.println("SQL Exception! Message: " + e.getMessage());
        }
        return id;

    }

    @Override
    public List<String> getCardsInPackage(String package_id){
        List<String> cards = new ArrayList<>();

        try (
                Connection con = database.getConnection();
                PreparedStatement pstmt = con.prepareStatement(GET_CARDS)
        ) {
            pstmt.setString(1, package_id);
            try (ResultSet rs = pstmt.executeQuery()){
                while(rs.next()){
                    cards.add(rs.getString("id"));
                }
            }
        }catch (SQLException e){
            System.err.println("SQL Exception! Message: " + e.getMessage());
        }
        return cards;

    }

    @Override
    public void updateCoins(String username, int costs) {

        try (
                Connection con = database.getConnection();
                PreparedStatement pstmt = con.prepareStatement(UPDATE_USER_COINS)
        ) {
            pstmt.setInt(1, costs);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
        }catch (SQLException e){
            System.err.println("SQL Exception! Message: " + e.getMessage());
        }
    }

    @Override
    public void delete(String package_id) {
        try (
                Connection con = database.getConnection();
                PreparedStatement pstmt = con.prepareStatement(DELETE)
        ) {
            pstmt.setString(1, package_id);
            pstmt.execute();
        }catch (SQLException e){
            System.err.println("SQL Exception! Message: " + e.getMessage());
        }
    }
}

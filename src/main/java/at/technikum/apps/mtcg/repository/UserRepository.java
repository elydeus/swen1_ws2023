package at.technikum.apps.mtcg.repository;

import at.technikum.apps.mtcg.entity.Card;
import at.technikum.apps.mtcg.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {


    Optional<User> login(String username, String password);

    boolean isValid(String username);

    User findByUsername(String username);

    User save(User user);

    User update(User user, String username);

    int findStats(String username);

    List<Integer> sortedEloList();
    String findUserString(String username);


}

package at.technikum.apps.mtcg.repository;

import at.technikum.apps.mtcg.entity.Card;
import at.technikum.apps.mtcg.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {


    Optional<User> find(int id);

    User findByUsername(String username);

    User save(User user);


}

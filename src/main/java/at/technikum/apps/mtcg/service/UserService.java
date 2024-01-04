package at.technikum.apps.mtcg.service;

import at.technikum.apps.mtcg.entity.Card;
import at.technikum.apps.mtcg.entity.User;
import at.technikum.apps.mtcg.repository.DatabaseUserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//service that lets you create and edit users
public class UserService {

    private final DatabaseUserRepository userRepository;

    public UserService(DatabaseUserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public boolean isValid(String username){
        return userRepository.isValid(username);
    }

    public User update(User updatedUser, String username){
        return userRepository.update(updatedUser, username);
    }

    public User registration(User user){
        user.setId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }
    public Optional<User> login(String username, String password){
        Optional<User> checkUser = userRepository.login(username, password);
        if(checkUser.isEmpty()){
            return Optional.empty();
        }
        return checkUser;
    }

    public int findStats(String username){
        return userRepository.findStats(username);
    }

    public List<Integer> sortedEloList(){
        return userRepository.sortedEloList();
    }

    public String findUserString(String username){
        return userRepository.findUserString(username);
    }
}

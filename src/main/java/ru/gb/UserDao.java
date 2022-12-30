package ru.gb;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    User findById(Long id);
//    Optional<User> findById(Long id);

    User findByName(String name);

//    Optional<User> findByName(String name);

    List<User> findAll();
    void save(User user);
    void updateNameById(Long id, String name);
}

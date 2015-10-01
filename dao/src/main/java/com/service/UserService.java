package com.service;

import com.models.User;
import java.util.List;

/**
 * Created by kzub on 9/22/2015.
 */
public interface UserService {
    void delete(long id);

    List<User> getAll();

    User findByPhone(String phone);

    void incrementCount(User user);

    void decrementCount(User user);

    void save(User newUser);

    void update(User user);

    void deleteByPhone(String phone);
}

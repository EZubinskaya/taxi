package com.service.impl;

import com.models.User;
import com.repository.UserRepository;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kzub on 9/24/2015.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void delete(long id) {
        userRepository.delete(id);
    }

    @Override
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User findByPhone(String phone) {
        List<User> users = (List<User>) userRepository.findAll();
        for(User curUser : users) {
            if(curUser.getPhone().equals(phone)) {
                return curUser;
            }
        }
        return null;
    }

    @Override
    public void incrementCount(User user) {
        user.setCount(user.getCount() + 1);
        userRepository.save(user);
    }

    @Override
    public void decrementCount(User user) {
        if(user.getCount() >= 1) {
            user.setCount(user.getCount() - 1);
            userRepository.save(user);
        }
    }

    @Override
    public void save(User newUser) {
        userRepository.save(newUser);
    }

    @Override
    public void update(User user) {
        if(userRepository.findOne(user.getId()) != null) {
            save(user);
        }
        save(user);
    }

    @Override
    public void deleteByPhone(String phone) {
        userRepository.delete(findByPhone(phone));
    }
}

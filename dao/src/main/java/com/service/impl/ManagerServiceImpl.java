package com.service.impl;

import com.models.Manager;
import com.repository.ManagerRepository;
import com.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kzub on 9/28/2015.
 */
@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerRepository managerRepository;


    @Override
    public List<Manager> findAll() {
        return (List<Manager>) managerRepository.findAll();
    }

    @Override
    public Manager findOne(long id) {
        return managerRepository.findOne(id);
    }

    @Override
    public void save(Manager manager) {
        managerRepository.save(manager);
    }

    @Override
    public Manager edit(Manager manager) {
        return managerRepository.save(manager);
    }

    @Override
    public void delete(long id) {
        managerRepository.delete(id);
    }
}

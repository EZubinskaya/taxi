package com.service;

import com.models.Manager;

import java.util.List;

/**
 * Created by kzub on 9/28/2015.
 */
public interface ManagerService {

    List<Manager> findAll();

    Manager findOne(long id);

    void save(Manager manager);

    Manager edit(Manager manager);

    void delete(long id);

}

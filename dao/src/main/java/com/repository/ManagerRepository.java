package com.repository;

import com.models.Manager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kzub on 9/28/2015.
 */
@Repository
public interface ManagerRepository extends CrudRepository<Manager, Long> {
}

package com.repository;

import com.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kzub on 9/18/2015.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}

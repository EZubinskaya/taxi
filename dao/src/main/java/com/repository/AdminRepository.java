package com.repository;

import com.models.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kzub on 9/24/2015.
 */
@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {
}

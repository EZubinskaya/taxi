package com.repository;

import com.models.UserDetail;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by kzub on 9/24/2015.
 */
//@Repository
public interface UserDetailRepository extends CrudRepository<UserDetail, Long> {
}

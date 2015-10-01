package com.repository;

import com.models.CarClass;
import com.models.Taxi;
import com.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kzub on 9/20/2015.
 */
@Repository
public interface TaxiReposotory  extends CrudRepository<Taxi, Long> {

    @Query(value = "SELECT t FROM taxi t where t.status='FREE'  AND t.carClass =:carClass")
    List<Taxi> findAllFreeTaxi(@Param("carClass") CarClass carClass);

    @Query(value = "SELECT t FROM taxi t where t.carRegistrationSign=:carRegistrationSign")
    Taxi findCarRegistrationNumber(@Param("carRegistrationSign") String carRegistrationSign);
}

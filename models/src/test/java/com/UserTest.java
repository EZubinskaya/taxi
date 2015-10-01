package com;

import com.models.User;

import static org.junit.Assert.*;

/**
 * Created by kzub on 9/17/2015.
 */
public class UserTest {

    @org.junit.Test
    public void testGetPhone() throws Exception {

        User user = new User();
        user.setName("n");
        assertEquals("n", user.getName());
    }
}
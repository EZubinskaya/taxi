package com.service;

import com.models.UserDetail;

/**
 * Created by kzub on 9/25/2015.
 */
public interface UserDetailService  {
    UserDetail getUser(String login);
}

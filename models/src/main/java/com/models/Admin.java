package com.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by kzub on 9/19/2015.
 */

@Entity(name = "admin")
@AttributeOverrides({
        @AttributeOverride(name="username", column=@Column(name="username")),
        @AttributeOverride(name="password", column=@Column(name="password")),
        @AttributeOverride(name="role", column=@Column(name="role"))
})
public class Admin extends UserDetail implements Serializable {}

package com.models;

/**
 * Created by kzub on 9/17/2015.
 */
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "manager")
@AttributeOverrides({
        @AttributeOverride(name="username", column=@Column(name="username")),
        @AttributeOverride(name="password", column=@Column(name="password")),
        @AttributeOverride(name="role", column=@Column(name="role"))
})
public class Manager extends UserDetail implements Serializable {}
